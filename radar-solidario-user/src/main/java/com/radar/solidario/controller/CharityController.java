package com.radar.solidario.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.radar.solidario.dto.OptionDTO;
import com.radar.solidario.dto.charity.CharityFRPDTO;
import com.radar.solidario.dto.charity.CharityRPDTO;
import com.radar.solidario.service.CharityService;
import com.radar.solidario.util.Response;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@NoArgsConstructor
@RequestMapping("/charity")
public class CharityController {

	@Autowired
	private CharityService charityService;

	@Cacheable("charity")
	@GetMapping(params = "id")
	public ResponseEntity<Response<CharityRPDTO>> findById(@RequestParam Long id) {
		log.info("Start - CharityController.findById - Id: {}", id);
		Response<CharityRPDTO> response = new Response<>();

		CharityRPDTO charity = this.charityService.findById(id);
		response.setData(charity);

		log.info("End - CharityController.findById - CharityRPDTO: {}", charity);
		return ResponseEntity.ok(response);
	}

	@Cacheable("charity")
	@GetMapping(params = "name")
	public ResponseEntity<Response<CharityRPDTO>> findByName(@RequestParam String name) {
		log.info("Start - CharityController.findByName - Name: {}", name);
		Response<CharityRPDTO> response = new Response<>();

		CharityRPDTO charity = this.charityService.findByName(name);
		response.setData(charity);

		log.info("End - CharityController.findByName - CharityRPDTO: {}", charity);
		return ResponseEntity.ok(response);
	}

	@Cacheable("charity")
	@GetMapping(value = "/options")
	public ResponseEntity<Response<List<OptionDTO<Long>>>> findOptions() {
		log.info("Start - CharityController.findOptions");
		Response<List<OptionDTO<Long>>> response = new Response<>();

		List<OptionDTO<Long>> options = this.charityService.findOptions();
		response.setData(options);

		log.info("End - CharityController.findOptions - List<OptionDTO<Long>>: {}", options);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<CharityRPDTO>> include(@RequestBody @Valid CharityRPDTO charityPDTO) {
		log.info("Start - CharityController.include - CharityPDTO: {}", charityPDTO);
		Response<CharityRPDTO> response = new Response<>();

		CharityRPDTO charity = this.charityService.include(charityPDTO);
		response.setData(charity);

		log.info("End - CharityController.include - CharityRPDTO: {}", charity);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping
	public ResponseEntity<Response<CharityRPDTO>> edit(@RequestBody @Valid CharityFRPDTO charityFRPDTO) {
		log.info("Start - CharityController.edit - CharityFRPDTO: {}", charityFRPDTO);
		Response<CharityRPDTO> response = new Response<>();

		CharityRPDTO charity = this.charityService.edit(charityFRPDTO);
		response.setData(charity);

		log.info("End - CharityController.edit - CharityRPDTO: {}", charity);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(params = "id")
	public ResponseEntity<Response<CharityRPDTO>> remove(@RequestParam Long id) {
		log.info("Start - CharityController.remove - Id: {}", id);
		Response<CharityRPDTO> response = new Response<>();

		CharityRPDTO charity = this.charityService.remove(id);
		response.setData(charity);

		log.info("End - CharityController.remove - CharityRPDTO: {}", charity);
		return ResponseEntity.ok(response);
	}
}
