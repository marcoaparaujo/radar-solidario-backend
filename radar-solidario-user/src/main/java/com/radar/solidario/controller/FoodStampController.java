package com.radar.solidario.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
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

import com.radar.solidario.dto.OptionDTO;
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
	public ResponseEntity<Response<Page<FoodStampHRDTO>>> findAll(@RequestParam(required = false) Long charityId,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int pageSize,
			@RequestParam(value = "order", defaultValue = "id") String order,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		log.info("Start - FoodStampController.findAll - Page: {}, PageSize: {}, Order: {}, Direction: {}");
		Response<Page<FoodStampHRDTO>> response = new Response<>();

		Pageable pageable = PageRequest.of(page, pageSize, Direction.valueOf(direction), order);

		Page<FoodStampHRDTO> foodStamps = this.foodStampService.findAll(pageable, charityId);
		response.setData(foodStamps);

		log.info("End - FoodStampController.findAll - Page<FoodStampHRDTO>: {}", foodStamps);
		return ResponseEntity.ok(response);
	}

	@Cacheable("food-stamp")
	@GetMapping(params = "date")
	public ResponseEntity<Response<List<FoodStampRDTO>>> findAllByDate(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd/MM/yyyy") LocalDate date) {
		log.info("Start - FoodStampController.findByDate - Date: {}", date);
		Response<List<FoodStampRDTO>> response = new Response<>();

		List<FoodStampRDTO> foodStamps = this.foodStampService.findAllByDate(date);
		response.setData(foodStamps);

		log.info("End - FoodStampController.findByDate - FoodStampRDTO: {}", foodStamps);
		return ResponseEntity.ok(response);
	}

	@Cacheable("food-stamp")
	@GetMapping(params = "name")
	public ResponseEntity<Response<List<FoodStampRDTO>>> findAllByCharityName(@RequestParam String name) {
		log.info("Start - FoodStampController.findAllByCharityName - Name: {}", name);
		Response<List<FoodStampRDTO>> response = new Response<>();

		List<FoodStampRDTO> foodStamps = this.foodStampService.findAllByCharityName(name);
		response.setData(foodStamps);

		log.info("End - FoodStampController.findAllByCharityName - FoodStampRDTO: {}", foodStamps);
		return ResponseEntity.ok(response);
	}

	@Cacheable("food-stamp")
	@GetMapping(params = "isAble")
	public ResponseEntity<Response<Page<FoodStampRDTO>>> findAllByIsAble(@RequestParam Boolean isAble,
			@RequestParam(required = false) Long charityId, @RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int pageSize,
			@RequestParam(value = "order", defaultValue = "id") String order,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		log.info("Start - FoodStampController.findAllByIsAble - Page: {}, PageSize: {}, Order: {}, Direction: {}", page,
				pageSize, order, direction);
		Response<Page<FoodStampRDTO>> response = new Response<>();

		Pageable pageable = PageRequest.of(page, pageSize, Direction.valueOf(direction), order);

		Page<FoodStampRDTO> foodStamps = this.foodStampService.findAllByIsAble(pageable, isAble, charityId);
		response.setData(foodStamps);

		log.info("End - FoodStampController.findAllByIsAble - Page<FoodStampRDTO>: {}", foodStamps);
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

	@Cacheable("food-stamp")
	@GetMapping(value = "/options")
	public ResponseEntity<Response<List<OptionDTO<Long>>>> findOptions() {
		log.info("Start - FoodStampController.findOptions");
		Response<List<OptionDTO<Long>>> response = new Response<>();

		List<OptionDTO<Long>> options = this.foodStampService.findOptions();
		response.setData(options);

		log.info("End - FoodStampController.findOptions - List<OptionDTO<Long>>: {}", options);
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
