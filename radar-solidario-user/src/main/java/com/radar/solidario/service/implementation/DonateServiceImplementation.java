package com.radar.solidario.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radar.solidario.dto.donate.DonateRDTO;
import com.radar.solidario.exception.donate.notFound.DonateNotFoundException;
import com.radar.solidario.repository.DonateRepository;
import com.radar.solidario.service.DonateService;
import com.radar.solidario.service.processor.DonateProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DonateServiceImplementation implements DonateService {

	@Autowired
	private DonateProcessor donateProcessor;

	@Autowired
	private DonateRepository donateRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<DonateRDTO> findAll() {
		log.info("Start - DonateServiceImplementation.findAll ");

		List<DonateRDTO> donateRDTOs = this.donateRepository.findAll().stream()
				.map(donate -> mapper.map(donate, DonateRDTO.class)).collect(Collectors.toList());
		if (donateRDTOs.isEmpty()) {
			throw new DonateNotFoundException();
		}

		log.info("End - DonateServiceImplementation.findAll - DonateRDTO: {}", donateRDTOs);
		return donateRDTOs;
	}

	@Override
	public DonateRDTO findById(Long id) {
		log.info("Start - DonateServiceImplementation.findById - Id: {}", id);

		DonateRDTO donateRDTO = mapper.map(this.donateProcessor.exists(id), DonateRDTO.class);

		log.info("End - DonateServiceImplementation.findById - DonateRDTO: {}", donateRDTO);
		return donateRDTO;
	}

}
