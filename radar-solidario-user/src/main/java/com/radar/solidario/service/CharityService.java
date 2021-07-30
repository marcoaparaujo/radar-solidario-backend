package com.radar.solidario.service;

import java.util.List;

import com.radar.solidario.dto.OptionDTO;
import com.radar.solidario.dto.charity.CharityFRPDTO;
import com.radar.solidario.dto.charity.CharityRPDTO;

public interface CharityService {

	List<CharityRPDTO> findAll();

	CharityRPDTO findById(Long id);

	CharityRPDTO findByName(String name);

	List<OptionDTO<Long>> findOptions();

	CharityRPDTO include(CharityRPDTO charityPDTO);
	
	CharityRPDTO edit(CharityFRPDTO charityRPDTO);

	CharityRPDTO remove(Long id);
}
