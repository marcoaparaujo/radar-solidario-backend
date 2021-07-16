package com.radar.solidario.service.processor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.radar.solidario.dto.foodStamp.FoodStampHRDTO;
import com.radar.solidario.entity.FoodStamp;
import com.radar.solidario.exception.foodStamp.notFound.FoodStampNotFoundException;
import com.radar.solidario.repository.FoodStampRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FoodStampProcessor {

	@Autowired
	private FoodStampRepository foodStampRepository;

	@Autowired
	ModelMapper mapper;

	public FoodStamp exists(Long id) {
		log.info("Start - FoodStampProcessor.exists - Id: {}", id);

		Optional<FoodStamp> optFoodStamp = this.foodStampRepository.findById(id);
		if (optFoodStamp.isEmpty()) {
			log.error("FoodStampNotFoundException - Id: {}", id);
			throw new FoodStampNotFoundException();
		}

		log.info("End - FoodStampProcessor.exists - FoodStamp: {}", optFoodStamp.get());
		return optFoodStamp.get();
	}


	public List<FoodStampHRDTO> exists() {
		log.info("Start - FoodStampProcessor.exists");

		List<FoodStampHRDTO> foodStampHRDTOs = this.foodStampRepository.findAll().stream()
				.map(food -> mapper.map(food, FoodStampHRDTO.class)).collect(Collectors.toList());
		if (foodStampHRDTOs.isEmpty()) {
			log.error("FoodStampNotFoundException");
			throw new FoodStampNotFoundException();
		}

		log.info("End - FoodStampProcessor.exists - FoodStamHDTO: {}", foodStampHRDTOs);
		return foodStampHRDTOs;
	}

	public void remove(Long id) {
		log.info("Start - FoodStampProcessor.remove - Id: {}", id);

		FoodStamp foodStamp = this.exists(id);
		this.foodStampRepository.delete(foodStamp);

		log.info("End - FoodStampProcessor.remove - FoodStamp: {}", foodStamp);

	}
}
