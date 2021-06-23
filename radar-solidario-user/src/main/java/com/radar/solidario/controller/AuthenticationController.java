package com.radar.solidario.controller;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.radar.solidario.dto.authentication.AuthenticationFRDTO;
import com.radar.solidario.service.AuthenticationService;
import com.radar.solidario.util.Response;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@NoArgsConstructor
@RequestMapping("/authentication")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;

	@Cacheable("authentication")
	@GetMapping(params = "id")
	public ResponseEntity<Response<AuthenticationFRDTO>> findById(@RequestParam Long id) {
		log.info("Start - AuthenticationController.findById - Id: {}", id);
		Response<AuthenticationFRDTO> response = new Response<>();

		AuthenticationFRDTO authentication = this.authenticationService.findById(id);
		response.setData(authentication);

		log.info("End - AuthenticationController.findById - AuthenticationFRDTO: {}", authentication);
		return ResponseEntity.ok(response);
	}

	@Cacheable("authentication")
	@GetMapping(params = "email")
	public ResponseEntity<Response<AuthenticationFRDTO>> findByEmail(@RequestParam @Email @Valid String email) {
		log.info("Start - AuthenticationController.findByEmail - Email: {}", email);
		Response<AuthenticationFRDTO> response = new Response<>();

		AuthenticationFRDTO authentication = this.authenticationService.findByEmail(email);
		response.setData(authentication);

		log.info("End - AuthenticationController.findByEmail - AuthenticationFRDTO: {}", authentication);
		return ResponseEntity.ok(response);
	}
}
