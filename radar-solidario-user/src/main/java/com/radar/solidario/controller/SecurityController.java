package com.radar.solidario.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.radar.solidario.dto.LoginDTO;
import com.radar.solidario.dto.token.TokenFRDTO;
import com.radar.solidario.dto.token.TokenRDTO;
import com.radar.solidario.service.SecurityService;
import com.radar.solidario.util.Response;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@NoArgsConstructor
@RequestMapping("/security")
public class SecurityController {

	@Autowired
	private SecurityService securityService;

	@PostMapping("/login")
	public ResponseEntity<Response<TokenFRDTO>> login(@Valid @RequestBody LoginDTO loginDTO) {
		log.info("Start - SecurityController.login - LoginDTO: {}", loginDTO);
		Response<TokenFRDTO> response = new Response<>();

		TokenFRDTO tokenFRDTO = this.securityService.login(loginDTO);
		response.setData(tokenFRDTO);

		log.info("End - SecurityController.login - TokenFRDTO: {}", tokenFRDTO);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/refresh")
	public ResponseEntity<Response<TokenRDTO>> refresh(
			@RequestHeader(required = true, name = "Authorization") String token) {
		log.info("Start - SecurityController.refresh - Token: {}", token);
		Response<TokenRDTO> response = new Response<>();

		TokenRDTO returnedToken = this.securityService.refresh(token);
		response.setData(returnedToken);

		log.info("End - SecurityController.refresh - TokenRDTO: {}", returnedToken);
		return ResponseEntity.ok(response);
	}

	@PatchMapping(value = "/lock", params = "id")
	public ResponseEntity<Response<TokenRDTO>> lock(@RequestParam Long id) {
		log.info("Start - SecurityController.lock - Id: {}", id);

		this.securityService.lock(id);

		log.info("End - SecurityController.lock");
		return ResponseEntity.noContent().build();
	}

	@PatchMapping(value = "/unlock", params = "id")
	public ResponseEntity<Response<TokenRDTO>> unlock(@RequestParam Long id) {
		log.info("Start - SecurityController.unlock - Id: {}", id);

		this.securityService.unlock(id);

		log.info("End - SecurityController.unlock");
		return ResponseEntity.noContent().build();
	}
}
