package com.radar.solidario.service.processor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.radar.solidario.entity.Family;
import com.radar.solidario.exception.family.notFound.FamilyNotFoundException;
import com.radar.solidario.repository.FamilyRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FamilyProcessor {

	@Autowired
	private FamilyRepository familyRepository;

	public Family exists(Long id) {
		log.info("Start - FamilyProcessor.exists - Id: {}", id);

		Optional<Family> optFamily = this.familyRepository.findById(id);
		if (optFamily.isEmpty()) {
			log.error("FamilyNotFoundException - Id: {}", id);
			throw new FamilyNotFoundException();
		}

		log.info("End - FamilyProcessor.exists - Family: {}", optFamily.get());
		return optFamily.get();
	}

	public Family existsCpf(String cpf) {
		log.info("Start - FamilyProcessor.existsCpf - CPF: {}", cpf);

		Optional<Family> optFamily = this.familyRepository.findByCpf(cpf);
		if (optFamily.isEmpty()) {
			log.error("FamilyNotFoundException - CPF: {}", cpf);
			throw new FamilyNotFoundException();
		}

		log.info("End - FamilyProcessor.existsCpf - Family: {}", optFamily.get());
		return optFamily.get();
	}

	public Family existsNis(String nis) {
		log.info("Start - FamilyProcessor.existsNis - NIS: {}", nis);

		Optional<Family> optFamily = this.familyRepository.findByNis(nis);
		if (optFamily.isEmpty()) {
			log.error("FamilyNotFoundException - NIS: {}", nis);
			throw new FamilyNotFoundException();
		}

		log.info("End - FamilyProcessor.existsNis - Family: {}", optFamily.get());
		return optFamily.get();
	}
}
