package com.radar.solidario.service;

import java.time.LocalDate;
import java.util.List;

import com.radar.solidario.dto.foodStamp.FoodStampHPDTO;
import com.radar.solidario.dto.foodStamp.FoodStampHRDTO;
import com.radar.solidario.dto.foodStamp.FoodStampPDTO;
import com.radar.solidario.dto.foodStamp.FoodStampRDTO;

public interface FoodStampService {

	List<FoodStampHRDTO> findAll();

	List<FoodStampRDTO> findAllByDate(LocalDate date);
	
	List<FoodStampRDTO> findAllByCharityName(String name);

	FoodStampRDTO findById(Long id);

	FoodStampHRDTO add(FoodStampPDTO foodStampPDTO);

	FoodStampHRDTO remove(FoodStampHPDTO foodStampHPDTO);
}
