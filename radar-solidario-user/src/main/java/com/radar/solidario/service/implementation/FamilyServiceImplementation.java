package com.radar.solidario.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radar.solidario.dto.family.FamilyHRDTO;
import com.radar.solidario.dto.family.FamilyPDTO;
import com.radar.solidario.dto.family.FamilyRDTO;
import com.radar.solidario.entity.Family;
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

	@Override
	public FamilyRDTO findById(Long id) {
		log.info("Start - FamilyProcessor.exists - Id: {}", id);

		Family family = this.familyProcessor.exists(id);
		FamilyRDTO familyRDTO = this.mapper.map(family, FamilyRDTO.class);

		log.info("End - FamilyProcessor.exists - FamilyRDTO: {}", familyRDTO);
		return familyRDTO;
	}

	@Override
	public FamilyRDTO findByNis(Long nis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FamilyRDTO findByCpf(String cpf) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FamilyHRDTO include(FamilyPDTO family) {
		// TODO Auto-generated method stub
		return null;
	}
}
