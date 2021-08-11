package com.radar.solidario.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.radar.solidario.dto.donate.DonateDTO;
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

	@GetMapping(params = "id")
	public ResponseEntity<Response<DonateDTO>> findLastFamilyDonationById(@RequestParam Long id) {
		log.info("Start - DonateController.findLastFamilyDonationById - Id: {}", id);
		Response<DonateDTO> response = new Response<>();
		
		DonateDTO donateDTO = this.donateService.findLastFamilyDonationById(id);
		response.setData(donateDTO);
		
		log.info("End - CharityController.findLastFamilyDonationById - DonateDTO: {}", donateDTO);
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
