package com.radar.solidario.service;

import java.util.List;

import com.radar.solidario.dto.donate.DonateDTO;
import com.radar.solidario.dto.donate.DonatePDTO;
import com.radar.solidario.dto.donate.DonateRDTO;

public interface DonateService {
	List<DonateRDTO> findAll();

	DonateRDTO findById(Long id);
	
	DonateRDTO donate(DonatePDTO donatePDTO);

	DonateDTO findLastFamilyDonationById(Long id);
}
