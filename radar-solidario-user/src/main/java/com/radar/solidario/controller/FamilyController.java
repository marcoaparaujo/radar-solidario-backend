package com.radar.solidario.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.radar.solidario.dto.family.FamilyHRDTO;
import com.radar.solidario.dto.family.FamilyPDTO;
import com.radar.solidario.dto.family.FamilyRDTO;
import com.radar.solidario.service.FamilyService;
import com.radar.solidario.util.Response;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@NoArgsConstructor
@RequestMapping("/family")
public class FamilyController {

	@Autowired
	private FamilyService familyService;

	@Cacheable("family")
	@GetMapping(params = "id")
	public ResponseEntity<Response<FamilyRDTO>> findById(@RequestParam Long id) {
		log.info("Start - FamilyController.findById - Id: {}", id);
		Response<FamilyRDTO> response = new Response<>();

		FamilyRDTO family = this.familyService.findById(id);
		response.setData(family);

		log.info("End - FamilyController.findById - FamilyRDTO: {}", family);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<FamilyHRDTO>> include(@RequestBody @Valid FamilyPDTO familyPDTO) {
		log.info("Start - FamilyController.register - FamilyPDTO: {}", familyPDTO);
		Response<FamilyHRDTO> response = new Response<>();

		FamilyHRDTO family = this.familyService.include(familyPDTO);
		response.setData(family);

		log.info("End - FamilyController.register - FamilyHRDTO: {}", family);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}