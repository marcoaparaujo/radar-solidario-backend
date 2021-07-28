package com.radar.solidario.service;

import java.util.List;

import com.radar.solidario.dto.charity.CharityPDTO;
import com.radar.solidario.dto.charity.CharityRDTO;

public interface CharityService {

	List<CharityRDTO> findAll();

	CharityRDTO findById(Long id);

	CharityRDTO findByName(String name);

	CharityRDTO include(CharityPDTO charityPDTO);

	CharityRDTO remove(Long id);
}
