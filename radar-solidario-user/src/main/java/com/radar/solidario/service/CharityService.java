package com.radar.solidario.service;

import java.util.List;

import com.radar.solidario.dto.charity.CharityPDTO;
import com.radar.solidario.dto.charity.CharityRDTO;

public interface CharityService {

	CharityRDTO findById(Long id);

	List<CharityRDTO> findAll();

	CharityPDTO include(CharityPDTO charityPDTO);

	void remove(Long id);

}
