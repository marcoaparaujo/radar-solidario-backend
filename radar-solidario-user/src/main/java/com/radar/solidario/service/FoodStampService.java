package com.radar.solidario.service;

import java.util.List;

import com.radar.solidario.dto.foodStamp.FoodStampHRDTO;
import com.radar.solidario.dto.foodStamp.FoodStampPDTO;
import com.radar.solidario.dto.foodStamp.FoodStampRDTO;
import com.radar.solidario.entity.FoodStamp;

public interface FoodStampService {
	List<FoodStamp> findAll();

	FoodStampRDTO findById(Long id);

	FoodStampRDTO findByDonate(Long id);

	FoodStampRDTO findByCharity(Long id);

	FoodStampRDTO findByDate(Long id);

	FoodStampHRDTO include(FoodStampPDTO foodStampPDTO);

	FoodStampHRDTO remove(Long id);

}
