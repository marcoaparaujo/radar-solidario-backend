package com.user.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.data.dto.user.UserRDTO;
import com.user.data.service.UserService;
import com.user.data.util.Response;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@NoArgsConstructor
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(params = "nis")
	public ResponseEntity<Response<UserRDTO>> findByNis(@RequestParam String nis) {
		log.info("Start - UserController.findByNis - NIS: {}", nis);
		Response<UserRDTO> response = new Response<>();

		UserRDTO userRDTO = this.userService.findByNis(nis);
		response.setData(userRDTO);

		log.info("End - UserController.findByNis - UserRDTO: {}", userRDTO);
		return ResponseEntity.ok(response);
	}

	@GetMapping(params = "name")
	public ResponseEntity<Response<List<UserRDTO>>> findAllByName(@RequestParam String name) {
		log.info("Start - UserController.findAllByName - Name: {}", name);
		Response<List<UserRDTO>> response = new Response<>();

		List<UserRDTO> userRDTOs = this.userService.findAllByName(name);
		response.setData(userRDTOs);

		log.info("End - UserController.findAllByName - List<UserRDTO>: {}", userRDTOs);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public BodyBuilder include(@RequestBody UserRDTO user) {
		this.userService.include(user);
		return ResponseEntity.ok();
	}
}
