package com.radar.solidario.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radar.solidario.dto.family.FamilyHRDTO;
import com.radar.solidario.dto.family.FamilyPDTO;
import com.radar.solidario.dto.family.FamilyRDTO;
import com.radar.solidario.entity.Family;
import com.radar.solidario.repository.FamilyRepository;
import com.radar.solidario.service.FamilyService;
import com.radar.solidario.service.processor.FamilyProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FamilyServiceImplementation implements FamilyService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private FamilyProcessor familyProcessor;

	@Autowired
	private FamilyRepository familyRepository;

	@Override
	public FamilyRDTO findById(Long id) {
		log.info("Start - FamilyProcessor.findById - Id: {}", id);

		Family family = this.familyProcessor.exists(id);
		FamilyRDTO familyRDTO = this.mapper.map(family, FamilyRDTO.class);

		log.info("End - FamilyProcessor.findById - FamilyRDTO: {}", familyRDTO);
		return familyRDTO;
	}

	@Override
	public FamilyRDTO findByNis(String nis) {
		log.info("Start - FamilyProcessor.findByNis - NIS: {}", nis);

		Family family = this.familyProcessor.existsNis(nis);
		FamilyRDTO familyRDTO = this.mapper.map(family, FamilyRDTO.class);

		log.info("End - FamilyProcessor.findByNis - FamilyRDTO: {}", familyRDTO);
		return familyRDTO;
	}

	@Override
	public FamilyRDTO findByCpf(String cpf) {
		log.info("Start - FamilyProcessor.findByCpf - CPF: {}", cpf);

		Family family = this.familyProcessor.existsCpf(cpf);
		FamilyRDTO familyRDTO = this.mapper.map(family, FamilyRDTO.class);

		log.info("End - FamilyProcessor.findByCpf - FamilyRDTO: {}", familyRDTO);
		return familyRDTO;
	}

	@Override
	public FamilyHRDTO include(FamilyPDTO familyPDTO) {
		log.info("Start - FamilyServiceImplementation.include - FamilyPDTO: {}", familyPDTO);

		Family family = this.mapper.map(familyPDTO, Family.class);
		family = this.familyRepository.save(family);

		FamilyHRDTO familyHRDTO = this.mapper.map(family, FamilyHRDTO.class);

		log.info("End - FamilyServiceImplementation.include - FamilyHRDTO: {}", familyHRDTO);
		return familyHRDTO;
	}
}
