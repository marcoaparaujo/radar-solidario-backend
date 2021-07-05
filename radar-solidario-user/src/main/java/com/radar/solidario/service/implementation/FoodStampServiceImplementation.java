package com.radar.solidario.service.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radar.solidario.dto.foodStamp.FoodStampHRDTO;
import com.radar.solidario.dto.foodStamp.FoodStampPDTO;
import com.radar.solidario.dto.foodStamp.FoodStampRDTO;
import com.radar.solidario.entity.FoodStamp;
import com.radar.solidario.service.FoodStampService;
import com.radar.solidario.service.processor.FoodStampProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FoodStampServiceImplementation implements FoodStampService {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private FoodStampProcessor foodStampProcessor;
	
	@Override
	public List<FoodStamp> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FoodStampRDTO findById(Long id) {
		log.info("Start - FoodStampProcessor.exists - Id: {}", id);

		FoodStamp foodStamp = this.foodStampProcessor.exists(id);
		FoodStampRDTO familyRDTO = this.mapper.map(foodStamp, FoodStampRDTO.class);

		log.info("End - FamilyProcessor.exists - FamilyRDTO: {}", familyRDTO);
		return null;
	}

	@Override
	public FoodStampRDTO findByDonate(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FoodStampRDTO findByCharity(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FoodStampRDTO findByDate(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FoodStampHRDTO include(FoodStampPDTO foodStampPDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FoodStampHRDTO remove(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
