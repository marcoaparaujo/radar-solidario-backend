package com.radar.solidario.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.radar.solidario.dto.OptionDTO;
import com.radar.solidario.dto.foodStamp.FoodStampHPDTO;
import com.radar.solidario.dto.foodStamp.FoodStampHRDTO;
import com.radar.solidario.dto.foodStamp.FoodStampPDTO;
import com.radar.solidario.dto.foodStamp.FoodStampRDTO;

public interface FoodStampService {

	Page<FoodStampHRDTO> findAll(Pageable pageable);

	List<FoodStampRDTO> findAllByDate(LocalDate date);

	List<FoodStampRDTO> findAllByCharityName(String name);

	Page<FoodStampRDTO> findAllByIsAble(Pageable pageable, Boolean isAble);

	FoodStampRDTO findById(Long id);

	List<OptionDTO<Long>> findOptions();

	FoodStampHRDTO add(FoodStampPDTO foodStampPDTO);

	FoodStampHRDTO remove(FoodStampHPDTO foodStampHPDTO);
}
