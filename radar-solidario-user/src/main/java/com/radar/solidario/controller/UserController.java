package com.radar.solidario.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.radar.solidario.dto.LoginDTO;
import com.radar.solidario.dto.token.TokenFRDTO;
import com.radar.solidario.dto.user.UserPDTO;
import com.radar.solidario.service.SecurityService;
import com.radar.solidario.service.UserService;
import com.radar.solidario.util.Response;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@NoArgsConstructor
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@PostMapping
	public ResponseEntity<Response<TokenFRDTO>> include(@RequestBody @Valid UserPDTO userPDTO) {
		log.info("Start - UserController.register - UserPDTO: {}", userPDTO);
		Response<TokenFRDTO> response = new Response<>();

		LoginDTO login = this.userService.includeVoluntary(userPDTO);
		TokenFRDTO token = this.securityService.login(login);
		response.setData(token);

		log.info("End - UserController.register - TokenFRDTO: {}", token);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
