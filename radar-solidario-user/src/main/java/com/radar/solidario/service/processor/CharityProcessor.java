package com.radar.solidario.service.processor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.radar.solidario.entity.Charity;
import com.radar.solidario.exception.charity.alreadyExists.CharityAlreadyExistsException;
import com.radar.solidario.exception.charity.notFound.CharityNotFoundException;
import com.radar.solidario.repository.CharityRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CharityProcessor {

	@Autowired
	private CharityRepository charityRepository;

	public Charity exists(Long id) {
		log.info("Start - CharityProcessor.exists - Id: {}", id);

		Optional<Charity> optCharity = this.charityRepository.findById(id);
		if (optCharity.isEmpty()) {
			log.error("CharityNotFoundException - Id: {}", id);
			throw new CharityNotFoundException();
		}

		log.info("End - CharityProcessor.exists - Charity: {}", optCharity.get());
		return optCharity.get();
	}

	public Charity exists(String name) {
		log.info("Start - CharityProcessor.exists - Name: {}", name);

		Optional<Charity> optCharity = this.charityRepository.findByName(name);
		if (optCharity.isEmpty()) {
			log.error("CharityNotFoundException - Name: {}", name);
			throw new CharityNotFoundException();
		}

		log.info("End - CharityProcessor.exists - Charity: {}", optCharity.get());
		return optCharity.get();
	}

	public void alreadyExists(String name) {
		log.info("Start - CharityProcessor.alreadyExists - Id: {}", name);

		Optional<Charity> charity = this.charityRepository.findByName(name);
		if (charity.isPresent()) {
			log.error("CharityAlreadyExistsException - Name: {}", name);
			throw new CharityAlreadyExistsException();
		}

		log.info("End - CharityProcessor.alreadyExists - Charity: {}", charity);
	}
}
