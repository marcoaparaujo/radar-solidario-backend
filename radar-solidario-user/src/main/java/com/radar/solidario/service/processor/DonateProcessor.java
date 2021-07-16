package com.radar.solidario.service.processor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.radar.solidario.entity.Donate;
import com.radar.solidario.exception.donate.notFound.DonateNotFoundException;
import com.radar.solidario.repository.DonateRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DonateProcessor {

	@Autowired
	private DonateRepository donateRepository;

	public Donate exists(Long id) {
		log.info("Start - DonateProcessor.exists - Id: {}", id);

		Optional<Donate> optDonate = this.donateRepository.findById(id);
		if (optDonate.isEmpty()) {
			log.error("DonateNotFoundException - Id: {}", id);
			throw new DonateNotFoundException();
		}

		log.info("End - DonateProcessor.exists - Donate: {}", optDonate.get());
		return optDonate.get();
	}

}
