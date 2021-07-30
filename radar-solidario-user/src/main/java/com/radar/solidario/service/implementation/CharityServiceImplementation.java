package com.radar.solidario.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radar.solidario.dto.OptionDTO;
import com.radar.solidario.dto.charity.CharityFRPDTO;
import com.radar.solidario.dto.charity.CharityRPDTO;
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
	public List<CharityRPDTO> findAll() {
		log.info("Start - CharityServiceImplementation.findAll");

		List<Charity> charities = this.charityRepository.findAll();
		List<CharityRPDTO> charitiesRDTO = charities.stream()
				.map(charity -> this.mapper.map(charity, CharityRPDTO.class)).collect(Collectors.toList());

		log.info("End - CharityServiceImplementation.findAll - List<CharityRPDTO>: {}", charitiesRDTO);
		return charitiesRDTO;
	}

	@Override
	public CharityRPDTO findById(Long id) {
		log.info("Start - CharityServiceImplementation.findById - Id: {}", id);

		Charity charity = this.charityProcessor.exists(id);
		CharityRPDTO charityRPDTO = this.mapper.map(charity, CharityRPDTO.class);

		log.info("End - CharityServiceImplementation.findById - CharityRPDTO: {}", charityRPDTO);
		return charityRPDTO;
	}

	@Override
	public CharityRPDTO findByName(String name) {
		log.info("Start - CharityServiceImplementation.findByName - Name: {}", name);

		Charity charity = this.charityProcessor.exists(name);
		CharityRPDTO charityRPDTO = this.mapper.map(charity, CharityRPDTO.class);

		log.info("End - CharityServiceImplementation.findByName - CharityRPDTO: {}", charityRPDTO);
		return charityRPDTO;
	}

	@Override
	public List<OptionDTO<Long>> findOptions() {
		log.info("Start - CharityServiceImplementation.findOptions");

		List<Charity> charities = this.charityRepository.findAll();
		List<OptionDTO<Long>> options = charities.stream()
				.map(sector -> OptionDTO.<Long>builder().text(sector.getName()).value(sector.getId()).build())
				.collect(Collectors.toList());

		log.info("End - CharityServiceImplementation.findOptions - List<OptionDTO<Long>>: {}", options);
		return options;
	}

	@Override
	public CharityRPDTO include(CharityRPDTO charityRPDTO) {
		log.info("Start - CharityServiceImplementation.include - CharityRPDTO: {}", charityRPDTO);

		this.charityProcessor.alreadyExists(charityRPDTO.getName());

		Charity charity = this.mapper.map(charityRPDTO, Charity.class);
		charity = this.charityRepository.save(charity);

		charityRPDTO = this.mapper.map(charity, CharityRPDTO.class);

		log.info("End - CharityServiceImplementation.include - CharityRPDTO: {}", charityRPDTO);
		return charityRPDTO;
	}

	@Override
	public CharityRPDTO edit(CharityFRPDTO charityFRPDTO) {
		log.info("Start - CharityServiceImplementation.edit - CharityFRPDTO: {}", charityFRPDTO);

		Charity charity = this.mapper.map(charityFRPDTO, Charity.class);
		
		charity = this.charityProcessor.merge(charity);
		charity = this.charityRepository.save(charity);

		CharityRPDTO charityRPDTO = this.mapper.map(charity, CharityRPDTO.class);

		log.info("End - CharityServiceImplementation.edit - CharityRPDTO: {}", charityRPDTO);
		return charityRPDTO;
	}

	@Override
	public CharityRPDTO remove(Long id) {
		log.info("Start - CharityServiceImplementation.remove - Id: {}", id);

		Charity charity = this.charityProcessor.exists(id);
		this.charityRepository.delete(charity);

		CharityRPDTO charityRPDTO = this.mapper.map(charity, CharityRPDTO.class);

		log.info("End - CharityServiceImplementation.remove - CharityRPDTO: {}", charityRPDTO);
		return charityRPDTO;
	}
}
