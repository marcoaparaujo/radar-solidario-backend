package com.radar.solidario.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radar.solidario.dto.donate.DonateRDTO;
import com.radar.solidario.entity.Donate;
import com.radar.solidario.repository.DonateRepository;
import com.radar.solidario.service.DonateService;
import com.radar.solidario.service.processor.DonateProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DonateServiceImplementation implements DonateService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private DonateProcessor donateProcessor;

	@Autowired
	private DonateRepository donateRepository;

	@Override
	public List<DonateRDTO> findAll() {
		log.info("Start - DonateServiceImplementation.findAll");

		List<Donate> donates = this.donateRepository.findAll();
		List<DonateRDTO> donatesRDTO = donates.stream().map(donate -> this.mapper.map(donate, DonateRDTO.class))
				.collect(Collectors.toList());

		log.info("End - DonateServiceImplementation.findAll - List<DonateRDTO>: {}", donatesRDTO);
		return donatesRDTO;
	}

	@Override
	public DonateRDTO findById(Long id) {
		log.info("Start - DonateServiceImplementation.findById - Id: {}", id);

		Donate donate = this.donateProcessor.exists(id);
		DonateRDTO donateRDTO = mapper.map(donate, DonateRDTO.class);

		log.info("End - DonateServiceImplementation.findById - DonateRDTO: {}", donateRDTO);
		return donateRDTO;
	}
}
