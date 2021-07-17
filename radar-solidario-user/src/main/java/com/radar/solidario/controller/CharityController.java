package com.radar.solidario.controller;

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

import com.radar.solidario.dto.foodStamp.FoodStampHRDTO;
import com.radar.solidario.dto.foodStamp.FoodStampPDTO;
import com.radar.solidario.dto.foodStamp.FoodStampRDTO;
import com.radar.solidario.service.FoodStampService;
import com.radar.solidario.util.Response;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@NoArgsConstructor
@RequestMapping("/charity")
public class CharityController {

	@Autowired
	FoodStampService foodStampService;

	@Cacheable("charity")
	@GetMapping(params = "id")
	public ResponseEntity<Response<FoodStampRDTO>> findById(@RequestParam Long id) {
		log.info("Start - CharityController.findById - Id: {}", id);
		Response<FoodStampRDTO> response = new Response<>();

		FoodStampRDTO foodStamp = this.foodStampService.findById(id);
		response.setData(foodStamp);

		log.info("End - CharityController.findById - FoodStampRDTO: {}", foodStamp);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<FoodStampHRDTO>> include(@RequestBody FoodStampPDTO foodStampPDTO) {
		log.info("Start - CharityController.register - FoodStampPDTO: {}", foodStampPDTO);
		Response<FoodStampHRDTO> response = new Response<>();

		FoodStampHRDTO foodStamp = this.foodStampService.include(foodStampPDTO);
		response.setData(foodStamp);

		log.info("End - CharityController.register - FoodStampHRDTO: {}", foodStamp);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
