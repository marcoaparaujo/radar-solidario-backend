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

import com.radar.solidario.dto.charity.CharityRPDTO;
import com.radar.solidario.entity.Charity;
import com.radar.solidario.repository.CharityRepository;
import com.radar.solidario.service.processor.CharityProcessor;

import properties.charity.CharityInstance;
import properties.charity.CharityProperties;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Service Implementation - Charity")
public class CharityServiceImplementationTest extends CharityProperties {

	@Mock
	private Charity charity;

	@Mock
	private CharityRPDTO charityRPDTO;

	@Spy
	private ModelMapper mapper;

	@Mock
	private CharityProcessor charityProcessor;

	@Mock
	private CharityRepository charityRepository;

	@InjectMocks
	private CharityServiceImplementation charityService;

	@BeforeEach
	public void init() {
		this.mapper = new ModelMapper();

		this.charity = CharityInstance.instace();
	}

	@Test
	@DisplayName("Find a charity by ID")
	public void findById() {
		when(this.charityProcessor.exists(ID)).thenReturn(this.charity);

		CharityRPDTO response = this.charityService.findById(ID);

		assertEquals(this.charityRPDTO, response);
	}

	@Test
	@DisplayName("Find a charity by NAME")
	public void findByName() {
		when(this.charityProcessor.exists(NAME)).thenReturn(this.charity);

		CharityRPDTO response = this.charityService.findByName(NAME);

		assertEquals(this.charityRPDTO, response);
	}

	@Test
	@DisplayName("Include a charity")
	public void include() {
		when(this.charityRepository.save(any())).thenReturn(this.charity);

		CharityRPDTO response = this.charityService.include(this.charityRPDTO);

		assertEquals(this.charityRPDTO, response);
	}

}
