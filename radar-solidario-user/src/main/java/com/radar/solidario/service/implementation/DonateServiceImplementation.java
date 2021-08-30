package com.radar.solidario.service.implementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radar.solidario.dto.donate.DonatePDTO;
import com.radar.solidario.dto.donate.DonateRDTO;
import com.radar.solidario.entity.Charity;
import com.radar.solidario.entity.Donate;
import com.radar.solidario.entity.Family;
import com.radar.solidario.entity.FoodStamp;
import com.radar.solidario.entity.User;
import com.radar.solidario.repository.DonateRepository;
import com.radar.solidario.service.DonateService;
import com.radar.solidario.service.processor.CharityProcessor;
import com.radar.solidario.service.processor.DonateProcessor;
import com.radar.solidario.service.processor.FamilyProcessor;
import com.radar.solidario.service.processor.FoodStampProcessor;
import com.radar.solidario.service.processor.UserProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DonateServiceImplementation implements DonateService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UserProcessor userProcessor;

	@Autowired
	private DonateProcessor donateProcessor;

	@Autowired
	private FamilyProcessor familyProcessor;

	@Autowired
	private CharityProcessor charityProcessor;

	@Autowired
	private FoodStampProcessor foodStampProcessor;

	@Autowired
	private DonateRepository donateRepository;

	@Override
	public List<DonateRDTO> findAll() {
		log.info("Start - DonateServiceImplementation.findAll");

		List<Donate> donates = this.donateRepository.findAll();
		List<DonateRDTO> donatesRDTO = donates.stream().map(donate -> this.mapper.map(donate, DonateRDTO.class))
				.collect(Collectors.toList());

		log.info("End - DonateServiceImplementation.findAll - List<DonateRDTO>: {}", donatesRDTO);
		return donatesRDTO;
	}

	@Override
	public DonateRDTO findById(Long id) {
		log.info("Start - DonateServiceImplementation.findById - Id: {}", id);

		Donate donate = this.donateProcessor.exists(id);
		DonateRDTO donateRDTO = mapper.map(donate, DonateRDTO.class);

		log.info("End - DonateServiceImplementation.findById - DonateRDTO: {}", donateRDTO);
		return donateRDTO;
	}

	@Override
	public DonateRDTO donate(DonatePDTO donatePDTO) {
		log.info("Start - DonateServiceImplementation.donate - DonatePDTO: {}", donatePDTO);

		User user = this.userProcessor.exists(donatePDTO.getUser().getId());
		Family family = this.familyProcessor.exists(donatePDTO.getFamily().getId());
		Charity charity = this.charityProcessor.exists(donatePDTO.getCharity().getId());

		List<FoodStamp> foodStamps = donatePDTO.getFoodStamps().stream().map(foodStampHPDTO -> {
			FoodStamp foodStamp = this.mapper.map(foodStampHPDTO, FoodStamp.class);
			return this.foodStampProcessor.mergeRemove(foodStamp);
		}).collect(Collectors.toList());

		Donate donate = Donate.builder().date(LocalDateTime.now()).user(user).charity(charity).family(family)
				.foodStamp(foodStamps).build();

		donate = this.donateRepository.save(donate);

		DonateRDTO donateRDTO = this.mapper.map(donate, DonateRDTO.class);

		log.info("End - DonateServiceImplementation.donate - DonateRDTO: {}", donateRDTO);
		return donateRDTO;
	}
}
