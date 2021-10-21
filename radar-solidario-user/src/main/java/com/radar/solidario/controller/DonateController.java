package com.radar.solidario.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.radar.solidario.dto.donate.DonateFRDTO;
import com.radar.solidario.dto.donate.DonatePDTO;
import com.radar.solidario.dto.donate.DonateRDTO;
import com.radar.solidario.service.DonateService;
import com.radar.solidario.util.Response;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@NoArgsConstructor
@RequestMapping("/donate")
public class DonateController {

	@Autowired
	private DonateService donateService;

	@Cacheable("donate")
	@GetMapping
	public ResponseEntity<Response<Page<DonateFRDTO>>> findAll(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int pageSize,
			@RequestParam(value = "order", defaultValue = "id") String order,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		log.info("Start - DonateController.findAll - Page: {}, PageSize: {}, Order: {}, Direction: {}", page, pageSize,
				order, direction);
		Response<Page<DonateFRDTO>> response = new Response<>();

		Pageable pageable = PageRequest.of(page, pageSize, Direction.valueOf(direction), order);

		Page<DonateFRDTO> donates = this.donateService.findAll(pageable);
		response.setData(donates);

		log.info("End - DonateController.findAll - Page<DonateFRDTO>: {}", donates);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<DonateRDTO>> donate(@RequestBody @Valid DonatePDTO donatePDTO) {
		log.info("Start - DonateController.donate - DonatePDTO: {}", donatePDTO);
		Response<DonateRDTO> response = new Response<>();

		DonateRDTO donate = this.donateService.donate(donatePDTO);
		response.setData(donate);

		log.info("End - DonateController.donate - DonateRDTO: {}", donate);
		return ResponseEntity.ok(response);
	}
}
