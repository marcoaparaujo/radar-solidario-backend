package com.radar.solidario.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.radar.solidario.dto.CreateTokenDTO;
import com.radar.solidario.dto.LoignDTO;
import com.radar.solidario.dto.authentication.AuthenticationFRPDTO;
import com.radar.solidario.entity.Funcionario;
import com.radar.solidario.service.AuthenticationService;
import com.radar.solidario.service.FuncionarioService;
import com.radar.solidario.util.JwtUtil;
import com.radar.solidario.util.Response;

@RestController
@RequestMapping("authentication")
public class AuthenticationController {

	private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private FuncionarioService funcionarioService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthenticationService authenticationService;

	public ResponseEntity<Response<CreateTokenDTO>> authenticate(@Valid @RequestBody LoignDTO loginDto,
			BindingResult result) throws NoSuchAlgorithmException {

		log.info("Iniciando busca da autenticação: {}", loginDto.toString());
		Response<CreateTokenDTO> response = new Response<CreateTokenDTO>();

		if (result.hasErrors()) {
			log.error("Erro validando dados de login da autenticação: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.addError(error.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);
		}

		AuthenticationFRPDTO authOpt = this.authenticationService.findByEmail(loginDto.getEmail());
		if (authOpt.equals(null)) {
			log.info("Autenticação não encontrada para o e-mail: {}", loginDto.getEmail());

			return ResponseEntity.status(403).body(response);
		}

		boolean isEqual = new BCryptPasswordEncoder().matches(loginDto.getPassword(), authOpt.getPassword());
		if (!isEqual) {
			log.info("Autenticação com a senha incorreta: {}", loginDto.getPassword());

			return ResponseEntity.status(403).body(response);
		}

		Optional<Funcionario> clientOpt = this.funcionarioService.findById(authOpt.getId());
		UsernamePasswordAuthenticationToken securityAuthentication = new UsernamePasswordAuthenticationToken(
				loginDto.getEmail(), loginDto.getPassword());

		org.springframework.security.core.Authentication authentication = authenticationManager
				.authenticate(securityAuthentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		CreateTokenDTO token = new CreateTokenDTO();
		token.setRole(authOpt.getRole());
		token.setEmail(authOpt.getEmail());
		token.setName(clientOpt.get().getName());

		UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getEmail());
		token.setToken(jwtUtil.create(userDetails));

		response.setData(token);
		return ResponseEntity.ok(response);

	}
}
