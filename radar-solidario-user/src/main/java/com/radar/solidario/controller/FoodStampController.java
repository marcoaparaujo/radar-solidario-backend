package com.radar.solidario.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.radar.solidario.dto.foodStamp.FoodStampHPDTO;
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
@RequestMapping("/food-stamp")
public class FoodStampController {

	@Autowired
	private FoodStampService foodStampService;

	@Cacheable("food-stamp")
	@GetMapping
	public ResponseEntity<Response<List<FoodStampHRDTO>>> findAll() {
		log.info("Start - FoodStampController.findAll");
		Response<List<FoodStampHRDTO>> response = new Response<>();

		List<FoodStampHRDTO> foodStamps = this.foodStampService.findAll();
		response.setData(foodStamps);

		log.info("End - FoodStampController.findAll - List<FoodStampHRDTO>: {}", foodStamps);
		return ResponseEntity.ok(response);
	}

	@Cacheable("food-stamp")
	@GetMapping(params = "date")
	public ResponseEntity<Response<List<FoodStampRDTO>>> findByDate(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd/MM/yyyy") LocalDate date) {
		log.info("Start - FoodStampController.findByDate - Date: {}", date);
		Response<List<FoodStampRDTO>> response = new Response<>();

		List<FoodStampRDTO> foodStamps = this.foodStampService.findAllByDate(date);
		response.setData(foodStamps);

		log.info("End - FoodStampController.findByDate - FoodStampRDTO: {}", foodStamps);
		return ResponseEntity.ok(response);
	}

	@Cacheable("food-stamp")
	@GetMapping(params = "id")
	public ResponseEntity<Response<FoodStampRDTO>> findById(@RequestParam Long id) {
		log.info("Start - FoodStampController.findById - Id: {}", id);
		Response<FoodStampRDTO> response = new Response<>();

		FoodStampRDTO foodStamp = this.foodStampService.findById(id);
		response.setData(foodStamp);

		log.info("End - FoodStampController.findById - FoodStampRDTO: {}", foodStamp);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<FoodStampHRDTO>> add(@RequestBody @Valid FoodStampPDTO foodStampPDTO) {
		log.info("Start - FoodStampController.add - FoodStampPDTO: {}", foodStampPDTO);
		Response<FoodStampHRDTO> response = new Response<>();

		FoodStampHRDTO foodStamp = this.foodStampService.add(foodStampPDTO);
		response.setData(foodStamp);

		log.info("End - FoodStampController.add - FoodStampHRDTO: {}", foodStamp);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@DeleteMapping
	public ResponseEntity<Response<FoodStampHRDTO>> remove(@RequestBody @Valid FoodStampHPDTO foodStampHPDTO) {
		log.info("Start - FoodStampController.remove - FoodStampHPDTO: {}", foodStampHPDTO);
		Response<FoodStampHRDTO> response = new Response<>();

		FoodStampHRDTO foodStamp = this.foodStampService.remove(foodStampHPDTO);
		response.setData(foodStamp);

		log.info("End - FoodStampController.remove - FoodStampHRDTO: {}", foodStamp);
		return ResponseEntity.ok(response);
	}
}
