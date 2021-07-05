package com.radar.solidario.service.processor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.radar.solidario.entity.FoodStamp;
import com.radar.solidario.exception.family.notFound.FamilyNotFoundException;
import com.radar.solidario.repository.FoodStampRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FoodStampProcessor {
	
	@Autowired
	private FoodStampRepository foodStampRepository;

	public FoodStamp exists(Long id) {
		log.info("Start - FoodStampProcessor.exists - Id: {}", id);

		Optional<FoodStamp> optFoodStamp = this.foodStampRepository.findById(id);
		if (optFoodStamp.isEmpty()) {
			log.error("FoodStampNotFoundException - Id: {}", id);
			throw new FamilyNotFoundException();
		}

		log.info("End - FoodStampProcessor.exists - Family: {}", optFoodStamp.get());
		return optFoodStamp.get();
	}
	
}
