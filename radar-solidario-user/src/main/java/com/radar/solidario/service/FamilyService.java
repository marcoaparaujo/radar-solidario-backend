package com.radar.solidario.service;

import com.radar.solidario.dto.family.FamilyHRDTO;
import com.radar.solidario.dto.family.FamilyPDTO;
import com.radar.solidario.dto.family.FamilyRDTO;

public interface FamilyService {
	FamilyRDTO findById(Long id);
	
	FamilyRDTO findByNis(String nis);
	
	FamilyRDTO findByCpf(String cpf);
	
	FamilyHRDTO include(FamilyPDTO family);
}
