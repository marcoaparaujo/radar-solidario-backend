package com.radar.solidario.service.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.radar.solidario.dto.family.FamilyHRDTO;
import com.radar.solidario.dto.family.FamilyPDTO;
import com.radar.solidario.dto.family.FamilyRDTO;
import com.radar.solidario.entity.Family;
import com.radar.solidario.repository.FamilyRepository;
import com.radar.solidario.service.processor.FamilyProcessor;

import properties.family.FamilyInstance;
import properties.family.FamilyProperties;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Service Implementation - Family")
public class FamilyServiceImplementationTest extends FamilyProperties {

	@Mock
	private Family family;

	@Mock
	private FamilyPDTO familyPDTO;

	@Mock
	private FamilyRDTO familyRDTO;

	@Mock
	private FamilyHRDTO familyHRDTO;

	@Spy
	private ModelMapper mapper;

	@Mock
	private FamilyProcessor familyProcessor;

	@Mock
	private FamilyRepository familyRepository;

	@InjectMocks
	private FamilyServiceImplementation familyService;

	@BeforeEach
	public void init() {
		this.mapper = new ModelMapper();

		this.family = FamilyInstance.instace();
		this.familyRDTO = FamilyInstance.instaceFamilyRDTO();
		this.familyPDTO = FamilyInstance.instanceFamilyPDTO();
		this.familyHRDTO = FamilyInstance.instaceFamilyHRDTO();
	}

	@Test
	@DisplayName("Find a family by Id")
	public void findById() {
		when(this.familyProcessor.exists(ID)).thenReturn(this.family);

		FamilyRDTO response = this.familyService.findById(ID);

		assertEquals(this.familyRDTO, response);
	}

	@Test
	@DisplayName("Find a family by NIS")
	public void findByNis() {
		when(this.familyProcessor.existsNis(NIS)).thenReturn(this.family);

		FamilyRDTO response = this.familyService.findByNis(NIS);

		assertEquals(this.familyRDTO, response);
	}

	@Test
	@DisplayName("Find a family by CPF")
	public void findByCpf() {
		when(this.familyProcessor.existsCpf(CPF)).thenReturn(this.family);

		FamilyRDTO response = this.familyService.findByCpf(CPF);

		assertEquals(this.familyRDTO, response);
	}

	@Test
	@DisplayName("Include a family")
	public void include() {
		when(this.familyRepository.save(any())).thenReturn(this.family);

		FamilyHRDTO response = this.familyService.include(this.familyPDTO);

		assertEquals(this.familyHRDTO, response);
	}
}
