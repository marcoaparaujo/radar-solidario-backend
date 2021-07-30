package com.radar.solidario.service.processor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.radar.solidario.entity.FoodStamp;
import com.radar.solidario.exception.foodStamp.notFound.FoodStampNotFoundException;
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
			throw new FoodStampNotFoundException();
		}

		log.info("End - FoodStampProcessor.exists - FoodStamp: {}", optFoodStamp.get());
		return optFoodStamp.get();
	}

	public FoodStamp exists(Double weight) {
		log.info("Start - FoodStampProcessor.exists - Weight: {}", weight);

		Optional<FoodStamp> optFoodStamp = this.foodStampRepository.findByWeight(weight);
		if (optFoodStamp.isEmpty()) {
			log.error("FoodStampNotFoundException - Weight: {}", weight);
			throw new FoodStampNotFoundException();
		}

		log.info("End - FoodStampProcessor.exists - FoodStamp: {}", optFoodStamp.get());
		return optFoodStamp.get();
	}

	public FoodStamp mergeAdd(FoodStamp foodStamp) {
		log.info("Start - FoodStampProcessor.exists - FoodStamp: {}", foodStamp);

		Optional<FoodStamp> optFoodStamp = this.foodStampRepository.findByWeight(foodStamp.getWeight());
		if (optFoodStamp.isPresent()) {
			Integer actualLenght = optFoodStamp.get().getLenght() + foodStamp.getLenght();
			optFoodStamp.get().setLenght(actualLenght);

			foodStamp = optFoodStamp.get();
		}

		log.info("End - FoodStampProcessor.exists - FoodStamp: {}", foodStamp);
		return foodStamp;
	}

	public FoodStamp mergeRemove(FoodStamp foodStamp) {
		log.info("Start - FoodStampProcessor.exists - FoodStamp: {}", foodStamp);

		Optional<FoodStamp> optFoodStamp = this.foodStampRepository.findByWeight(foodStamp.getWeight());
		if (optFoodStamp.isPresent()) {
			Integer actualLenght = Math.max(0, optFoodStamp.get().getLenght() - foodStamp.getLenght());
			optFoodStamp.get().setLenght(actualLenght);

			foodStamp = optFoodStamp.get();
		}

		log.info("End - FoodStampProcessor.exists - FoodStamp: {}", foodStamp);
		return foodStamp;
	}
}
