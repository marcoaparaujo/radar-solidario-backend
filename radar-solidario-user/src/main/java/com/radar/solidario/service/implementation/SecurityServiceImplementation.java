package com.radar.solidario.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radar.solidario.dto.LoginDTO;
import com.radar.solidario.dto.token.TokenFRDTO;
import com.radar.solidario.dto.token.TokenRDTO;
import com.radar.solidario.entity.Authentication;
import com.radar.solidario.repository.AuthenticationRepository;
import com.radar.solidario.service.SecurityService;
import com.radar.solidario.service.processor.AuthenticationProcessor;
import com.radar.solidario.service.processor.SecurityProcessor;
import com.radar.solidario.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SecurityServiceImplementation implements SecurityService {

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private SecurityProcessor securityProcessor;

	@Autowired
	private AuthenticationProcessor authenticationProcessor;

	@Autowired
	private AuthenticationRepository authenticationRepository;

	@Override
	public TokenFRDTO login(LoginDTO loginDTO) {
		log.info("Start - SecurityServiceImplementation.login - LoginDTO: {}", loginDTO);

		Authentication authentication = this.securityProcessor.check(loginDTO.getEmail());
		this.securityProcessor.matchPassword(loginDTO.getPassword(), authentication.getPassword());

		String token = this.securityProcessor.authenticate(loginDTO.getEmail(), loginDTO.getPassword());
		TokenFRDTO tokenFRDTO = TokenFRDTO.builder().name(authentication.getUser().getName()).token(token)
				.roles(authentication.getRole()).build();

		log.info("End - SecurityServiceImplementation.login - TokenFRDTO: {}", tokenFRDTO);
		return tokenFRDTO;
	}

	@Override
	public TokenRDTO refresh(String token) {
		log.info("Start - SecurityServiceImplementation.refresh - Token: {}", token);

		token = token.substring(7);
		String refreshedToken = this.jwtTokenUtil.refresh(token);

		TokenRDTO returnedToken = new TokenRDTO(refreshedToken);

		log.info("End - SecurityServiceImplementation.refresh - TokenRDTO: {}", returnedToken);
		return returnedToken;
	}

	@Override
	public void lock(Long id) {
		log.info("Start - SecurityServiceImplementation.lock - Id: {}", id);

		Authentication authentication = this.authenticationProcessor.exists(id);
		authentication.setIsLocked(true);

		this.authenticationRepository.save(authentication);

		log.info("End - SecurityServiceImplementation.lock");
	}

	@Override
	public void unlock(Long id) {
		log.info("Start - SecurityServiceImplementation.unlock - Id: {}", id);

		Authentication authentication = this.authenticationProcessor.exists(id);
		authentication.setIsLocked(false);

		this.authenticationRepository.save(authentication);

		log.info("End - SecurityServiceImplementation.unlock");
	}
}
