package com.radar.solidario.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.radar.solidario.dto.donate.DonateFRDTO;
import com.radar.solidario.dto.donate.DonatePDTO;
import com.radar.solidario.dto.donate.DonateRDTO;

public interface DonateService {

	List<DonateRDTO> findAll();

	Page<DonateFRDTO> findAll(Pageable pageable);

	DonateRDTO findById(Long id);

	DonateRDTO donate(DonatePDTO donatePDTO);
}
