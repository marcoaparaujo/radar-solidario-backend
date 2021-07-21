package com.radar.solidario.service.implementation;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radar.solidario.dto.foodStamp.FoodStampHRDTO;
import com.radar.solidario.dto.foodStamp.FoodStampPDTO;
import com.radar.solidario.dto.foodStamp.FoodStampRDTO;
import com.radar.solidario.entity.FoodStamp;
import com.radar.solidario.exception.foodStamp.notFound.FoodStampNotFoundException;
import com.radar.solidario.repository.FoodStampRepository;
import com.radar.solidario.service.FoodStampService;
import com.radar.solidario.service.processor.FoodStampProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FoodStampServiceImplementation implements FoodStampService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private FoodStampProcessor foodStampProcessor;

	@Autowired
	private FoodStampRepository foodStampRepository;

	@Override
	public List<FoodStampHRDTO> findAll() {
		log.info("Start - FoodStampServiceImplementation.findAll - FoodStampHRDTO");

		List<FoodStampHRDTO> foodStampHRDTOs = this.foodStampProcessor.exists();

		log.info("End - FoodStampServiceImplementation.findAll - FoodStampHRDTO: {}", foodStampHRDTOs);
		return foodStampHRDTOs;
	}

	@Override
	public FoodStampRDTO findById(Long id) {
		log.info("Start - FoodStampServiceImplementation.findById - Id: {}", id);

		FoodStampRDTO foodStampRDTO = this.mapper.map(this.foodStampProcessor.exists(id), FoodStampRDTO.class);

		log.info("End - FoodStampServiceImplementation.findById - FoodStampRDTO: {}", foodStampRDTO);
		return foodStampRDTO;
	}

	@Override
	public List<FoodStampRDTO> findByDate(LocalDate date) {
		log.info("Start - FoodStampServiceImplementation.findByDate - Date: {}", date);

		Date data = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());

		List<FoodStamp> foodStamps = this.foodStampRepository.findByDate(data);
		if (foodStamps.isEmpty()) {
			throw new FoodStampNotFoundException();
		}
		List<FoodStampRDTO> foodStampRDTOs = foodStamps.stream().map(food -> this.mapper.map(food, FoodStampRDTO.class))
				.collect(Collectors.toList());

		log.info("End - FoodStampServiceImplementation.findByDate - FoodStampRDTO: {}", foodStamps);
		return foodStampRDTOs;
	}

	@Override
	public FoodStampHRDTO include(FoodStampPDTO foodStampPDTO) {
		log.info("Start - FoodStampServiceImplementation.include - FoodStampPDTO: {}", foodStampPDTO);

		FoodStamp foodStamp = this.mapper.map(foodStampPDTO, FoodStamp.class);
		this.foodStampRepository.save(foodStamp);

		FoodStampHRDTO foodStampHRDTO = this.mapper.map(foodStamp, FoodStampHRDTO.class);

		log.info("End - FoodStampServiceImplementation.include - FoodStampHRDTO: {}", foodStampHRDTO);
		return foodStampHRDTO;
	}

	@Override
	public void remove(Long id) {
		log.info("Start - FoodStampServiceImplementation.remove - FoodStamp - Id: {}", id);

		this.foodStampProcessor.remove(id);

		log.info("End - FoodStampServiceImplementation.remove - FoodStamp - Id: {}", id);
	}

}
