package com.radar.solidario.service.processor;

import java.util.List;
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

		log.info("End - CharityProcessor.exists - Chariry: {}", optCharity.get());
		return optCharity.get();
	}

	public void alreadyExists(String name) {
		log.info("Start - CharityProcessor.existsName - Id: {}", name);

		Optional<Charity> charity = charityRepository.findByName(name);
		if (charity.isPresent()) {
			log.error("CharityAlreadyExistsException - Name: {}", name);
			throw new CharityAlreadyExistsException();
		}

		log.info("End - CharityProcessor.existsName - Chariry: {}", charity);
	}

	public List<Charity> exists() {
		log.info("Start - CharityProcessor.exists ");

		List<Charity> charities = this.charityRepository.findAll();
		if (charities.isEmpty()) {
			log.error("CharityNotFoundException");
			throw new CharityNotFoundException();
		}

		log.info("End - CharityProcessor.exists - Charities: {}", charities);
		return charities;
	}

	public void remove(Long id) {
		log.info("Start - CharityProcessor.remove - Charity - Id: {} ", id);

		Charity charity = this.exists(id);
		this.charityRepository.delete(charity);

		log.info("End - CharityProcessor.remove - Charity - Id: {}", id);
	}

}
