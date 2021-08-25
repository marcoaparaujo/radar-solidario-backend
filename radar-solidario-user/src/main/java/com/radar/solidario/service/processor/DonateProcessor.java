package com.radar.solidario.service.processor;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.radar.solidario.dto.donate.DonatePrevisionDTO;
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

	public DonatePrevisionDTO getLastDonate(List<Donate> donates) {
		log.info("Start - DonateProcessor.getLastDonate - Donates: {}", donates);

		DonatePrevisionDTO donatePrevision = new DonatePrevisionDTO();
		if (!donates.isEmpty()) {
			donates.sort(Comparator.comparing(Donate::getDate));

			Donate lastDonate = donates.get(donates.size() - 1);
			LocalDateTime nextDonate = LocalDateTime.now().plusMonths(1);

			donatePrevision = DonatePrevisionDTO.builder().lastDonateDate(lastDonate.getDate())
					.nextDonateDate(nextDonate).build();
		}

		log.info("End - DonateProcessor.getLastDonate - DonatePrevisionDTO: {}", donatePrevision);
		return donatePrevision;
	}
}
