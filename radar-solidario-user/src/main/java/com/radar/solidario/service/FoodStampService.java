package com.radar.solidario.service;

import java.time.LocalDate;
import java.util.List;

import com.radar.solidario.dto.foodStamp.FoodStampHRDTO;
import com.radar.solidario.dto.foodStamp.FoodStampPDTO;
import com.radar.solidario.dto.foodStamp.FoodStampRDTO;

public interface FoodStampService {
	List<FoodStampHRDTO> findAll();

	FoodStampRDTO findById(Long id);

	List<FoodStampRDTO> findByDate(LocalDate date);

	FoodStampHRDTO include(FoodStampPDTO foodStampPDTO);

	void remove(Long id);

}
