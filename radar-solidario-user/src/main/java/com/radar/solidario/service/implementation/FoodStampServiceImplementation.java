package com.radar.solidario.service.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.radar.solidario.dto.OptionDTO;
import com.radar.solidario.dto.foodStamp.FoodStampHPDTO;
import com.radar.solidario.dto.foodStamp.FoodStampHRDTO;
import com.radar.solidario.dto.foodStamp.FoodStampPDTO;
import com.radar.solidario.dto.foodStamp.FoodStampRDTO;
import com.radar.solidario.entity.Charity;
import com.radar.solidario.entity.FoodStamp;
import com.radar.solidario.repository.FoodStampRepository;
import com.radar.solidario.service.FoodStampService;
import com.radar.solidario.service.processor.CharityProcessor;
import com.radar.solidario.service.processor.FoodStampProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FoodStampServiceImplementation implements FoodStampService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private CharityProcessor charityProcessor;

	@Autowired
	private FoodStampProcessor foodStampProcessor;

	@Autowired
	private FoodStampRepository foodStampRepository;

	@Override
	public Page<FoodStampHRDTO> findAll(Pageable pageable) {
		log.info("Start - FoodStampServiceImplementation.findAll - Pageable: {}", pageable);

		Page<FoodStamp> foodStamps = this.foodStampRepository.findAll(pageable);
		Page<FoodStampHRDTO> foodStampsHRDTO = foodStamps
				.map(foodStamp -> this.mapper.map(foodStamp, FoodStampHRDTO.class));

		log.info("End - FoodStampServiceImplementation.findAll - Page<FoodStampHRDTO>: {}", foodStampsHRDTO);
		return foodStampsHRDTO;
	}

	@Override
	public List<FoodStampRDTO> findAllByDate(LocalDate date) {
		log.info("Start - FoodStampServiceImplementation.findAllByDate - Date: {}", date);

		List<FoodStamp> foodStamps = this.foodStampRepository.findAllByDate(date);
		List<FoodStampRDTO> foodStampsRDTO = foodStamps.stream()
				.map(foodStamp -> this.mapper.map(foodStamp, FoodStampRDTO.class)).collect(Collectors.toList());

		log.info("End - FoodStampServiceImplementation.findAllByDate - List<FoodStampRDTO>: {}", foodStampsRDTO);
		return foodStampsRDTO;
	}

	@Override
	public List<FoodStampRDTO> findAllByCharityName(String name) {
		log.info("Start - FoodStampServiceImplementation.findAllByCharityName - Name: {}", name);

		List<FoodStamp> foodStamps = this.foodStampRepository.findAllByCharityName(name);
		List<FoodStampRDTO> foodStampsRDTO = foodStamps.stream()
				.map(foodStamp -> this.mapper.map(foodStamp, FoodStampRDTO.class)).collect(Collectors.toList());

		log.info("End - FoodStampServiceImplementation.findAllByCharityName - List<FoodStampRDTO>: {}", foodStampsRDTO);
		return foodStampsRDTO;
	}

	@Override
	public Page<FoodStampRDTO> findAllByIsAble(Pageable pageable, Boolean isAble) {
		log.info("Start - FoodStampServiceImplementation.findAll - Pageable: {}, IsAble: {}", pageable, isAble);

		Page<FoodStamp> foodStamps = this.foodStampRepository.findAllByIsAble(pageable, isAble);
		Page<FoodStampRDTO> foodStampsRDTO = foodStamps
				.map(foodStamp -> this.mapper.map(foodStamp, FoodStampRDTO.class));

		log.info("End - FoodStampServiceImplementation.findAll - Page<FoodStampRDTO>: {}", foodStampsRDTO);
		return foodStampsRDTO;
	}

	@Override
	public FoodStampRDTO findById(Long id) {
		log.info("Start - FoodStampServiceImplementation.findById - Id: {}", id);

		FoodStamp foodStamp = this.foodStampProcessor.exists(id);
		FoodStampRDTO foodStampRDTO = this.mapper.map(foodStamp, FoodStampRDTO.class);

		log.info("End - FoodStampServiceImplementation.findById - FoodStampRDTO: {}", foodStampRDTO);
		return foodStampRDTO;
	}

	@Override
	public List<OptionDTO<Long>> findOptions() {
		log.info("Start - FoodStampServiceImplementation.findOptions");

		List<FoodStamp> foodStamps = this.foodStampRepository.findAll();
		List<OptionDTO<Long>> options = foodStamps.stream().map(foodStamp -> OptionDTO.<Long>builder()
				.text(foodStamp.getWeight().toString()).value(foodStamp.getId()).build()).collect(Collectors.toList());

		log.info("End - FoodStampServiceImplementation.findOptions - List<OptionDTO<Long>>: {}", options);
		return options;
	}

	@Override
	public FoodStampHRDTO add(FoodStampPDTO foodStampPDTO) {
		log.info("Start - FoodStampServiceImplementation.add - FoodStampPDTO: {}", foodStampPDTO);

		Charity charity = this.charityProcessor.exists(foodStampPDTO.getCharity().getId());

		FoodStamp foodStamp = this.mapper.map(foodStampPDTO, FoodStamp.class);
		foodStamp.setCharity(charity);
		foodStamp.setDate(LocalDate.now());

		foodStamp = this.foodStampProcessor.mergeAdd(foodStamp);
		foodStamp = this.foodStampRepository.save(foodStamp);

		FoodStampHRDTO foodStampHRDTO = this.mapper.map(foodStamp, FoodStampHRDTO.class);

		log.info("End - FoodStampServiceImplementation.add - FoodStampHRDTO: {}", foodStampHRDTO);
		return foodStampHRDTO;
	}

	@Override
	public FoodStampHRDTO remove(FoodStampHPDTO foodStampHPDTO) {
		log.info("Start - FoodStampServiceImplementation.remove - FoodStampPDTO: {}", foodStampHPDTO);

		FoodStamp foodStamp = this.mapper.map(foodStampHPDTO, FoodStamp.class);
		foodStamp = this.foodStampProcessor.mergeRemove(foodStamp);

		foodStamp = this.foodStampRepository.save(foodStamp);

		FoodStampHRDTO foodStampHRDTO = this.mapper.map(foodStamp, FoodStampHRDTO.class);

		log.info("End - FoodStampServiceImplementation.remove - FoodStampHRDTO: {}", foodStampHRDTO);
		return foodStampHRDTO;
	}
}
