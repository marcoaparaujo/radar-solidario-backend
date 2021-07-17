package com.radar.solidario.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radar.solidario.dto.charity.CharityPDTO;
import com.radar.solidario.dto.charity.CharityRDTO;
import com.radar.solidario.entity.Charity;
import com.radar.solidario.repository.CharityRepository;
import com.radar.solidario.service.CharityService;
import com.radar.solidario.service.processor.CharityProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CharityServiceImplementation implements CharityService {

	@Autowired
	private CharityProcessor charityProcessor;
	
	@Autowired
	private CharityRepository charityRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public CharityRDTO findById(Long id) {
		log.info("Start - CharityServiceImplementation.findById - Id: {}", id);

		CharityRDTO charityRDTO = this.mapper.map(charityProcessor.exists(id), CharityRDTO.class);

		log.info("End - CharityServiceImplementation.findById - CharityRDTO: {}", charityRDTO);
		return charityRDTO;
	}

	@Override
	public List<CharityRDTO> findAll() {
		log.info("Start - CharityServiceImplementation.findAll");

		List<CharityRDTO> charityRDTOs = charityProcessor.exists().stream()
				.map(charity -> mapper.map(charity, CharityRDTO.class)).collect(Collectors.toList());

		log.info("End - CharityServiceImplementation.findALl - CharityRDTO: {}", charityRDTOs);
		return charityRDTOs;
	}

	@Override
	public CharityPDTO include(CharityPDTO charityPDTO) {
		log.info("Start - CharityServiceImplementation.include");
		
		this.charityProcessor.alreadyExists(charityPDTO.getName());
		Charity charity = mapper.map(charityPDTO, Charity.class);
		this.charityRepository.save(charity);		
		
		log.info("End - CharityServiceImplementation.include - CharityPDTO: {}", charityPDTO);
		return charityPDTO;
	}

	@Override
	public void remove(Long id) {
		log.info("Start - CharityServiceImplementation.remove - Charity -Id: {}", id);

		this.charityProcessor.remove(id);

		log.info("End - CharityServiceImplementation.remove - Charity - Id: {}", id);

	}

}
