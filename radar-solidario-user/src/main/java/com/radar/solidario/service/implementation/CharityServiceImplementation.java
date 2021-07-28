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
	private ModelMapper mapper;

	@Autowired
	private CharityProcessor charityProcessor;

	@Autowired
	private CharityRepository charityRepository;

	@Override
	public List<CharityRDTO> findAll() {
		log.info("Start - CharityServiceImplementation.findAll");

		List<Charity> charities = this.charityRepository.findAll();
		List<CharityRDTO> charitiesRDTO = charities.stream().map(charity -> this.mapper.map(charity, CharityRDTO.class))
				.collect(Collectors.toList());

		log.info("End - CharityServiceImplementation.findAll - List<CharityRDTO>: {}", charitiesRDTO);
		return charitiesRDTO;
	}

	@Override
	public CharityRDTO findById(Long id) {
		log.info("Start - CharityServiceImplementation.findById - Id: {}", id);

		Charity charity = this.charityProcessor.exists(id);
		CharityRDTO charityRDTO = this.mapper.map(charity, CharityRDTO.class);

		log.info("End - CharityServiceImplementation.findById - CharityRDTO: {}", charityRDTO);
		return charityRDTO;
	}

	@Override
	public CharityRDTO findByName(String name) {
		log.info("Start - CharityServiceImplementation.findByName - Name: {}", name);

		Charity charity = this.charityProcessor.exists(name);
		CharityRDTO charityRDTO = this.mapper.map(charity, CharityRDTO.class);

		log.info("End - CharityServiceImplementation.findByName - CharityRDTO: {}", charityRDTO);
		return charityRDTO;
	}

	@Override
	public CharityRDTO include(CharityPDTO charityPDTO) {
		log.info("Start - CharityServiceImplementation.include - CharityPDTO: {}", charityPDTO);

		this.charityProcessor.alreadyExists(charityPDTO.getName());

		Charity charity = this.mapper.map(charityPDTO, Charity.class);
		charity = this.charityRepository.save(charity);

		CharityRDTO charityRDTO = this.mapper.map(charity, CharityRDTO.class);

		log.info("End - CharityServiceImplementation.include - CharityRDTO: {}", charityRDTO);
		return charityRDTO;
	}

	@Override
	public CharityRDTO remove(Long id) {
		log.info("Start - CharityServiceImplementation.remove - Id: {}", id);

		Charity charity = this.charityProcessor.exists(id);
		this.charityRepository.delete(charity);

		CharityRDTO charityRDTO = this.mapper.map(charity, CharityRDTO.class);

		log.info("End - CharityServiceImplementation.remove - CharityRDTO: {}", charityRDTO);
		return charityRDTO;
	}
}
