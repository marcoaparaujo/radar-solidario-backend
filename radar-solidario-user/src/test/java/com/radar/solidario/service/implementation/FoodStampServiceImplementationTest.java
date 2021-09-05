package com.radar.solidario.service.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.radar.solidario.dto.OptionDTO;
import com.radar.solidario.dto.foodStamp.FoodStampRDTO;
import com.radar.solidario.entity.FoodStamp;
import com.radar.solidario.repository.FoodStampRepository;
import com.radar.solidario.service.processor.CharityProcessor;
import com.radar.solidario.service.processor.FoodStampProcessor;

import properties.foodStamp.FoodStampInstance;
import properties.foodStamp.FoodStampProperties;
import properties.option.OptionInstance;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Service Implementation - Family")
public class FoodStampServiceImplementationTest extends FoodStampProperties {

	@Mock
	private FoodStamp foodStamp;

	@Mock
	private List<FoodStamp> foodStamps;

	@Mock
	private FoodStampRDTO foodStampRDTO;

	@Mock
	private List<FoodStampRDTO> foodStampRDTOs;

	@Mock
	private List<OptionDTO<Long>> options;

	@Spy
	private ModelMapper mapper;

	@Mock
	private CharityProcessor charityProcessor;

	@Mock
	private FoodStampProcessor foodStampProcessor;

	@Mock
	private FoodStampRepository foodStampRepository;

	@InjectMocks
	private FoodStampServiceImplementation foodStampService;

	@BeforeEach
	public void init() {
		this.mapper = new ModelMapper();

		this.foodStamp = FoodStampInstance.instace();
		this.foodStampRDTO = FoodStampInstance.instaceRDTO();
		this.foodStampRDTOs = FoodStampInstance.instaceFoodStampRDTOs(5);
		this.foodStamps = FoodStampInstance.instaceFoodStamps(5);

		this.options = OptionInstance.instaceOptions(5);
	}

	@Test
	@DisplayName("Find a food by Id")
	public void findById() {
		when(this.foodStampProcessor.exists(ID)).thenReturn(this.foodStamp);

		FoodStampRDTO response = this.foodStampService.findById(ID);

		assertEquals(this.foodStampRDTO, response);
	}

	@Test
	@DisplayName("Find a food by date")
	public void findAllByDate() {
		when(this.foodStampRepository.findAllByDate(DATE)).thenReturn(this.foodStamps);

		List<FoodStampRDTO> response = this.foodStampService.findAllByDate(DATE);

		assertTrue(this.foodStampRDTOs.size() == response.size() && this.foodStampRDTOs.containsAll(response));
	}

	@Test
	@DisplayName("Find a food by charity name")
	public void findAllByCharityName() {
		when(this.foodStampRepository.findAllByCharityName(NAME)).thenReturn(this.foodStamps);

		List<FoodStampRDTO> response = this.foodStampService.findAllByCharityName(NAME);

		assertTrue(this.foodStampRDTOs.size() == response.size() && this.foodStampRDTOs.containsAll(response));
	}

	@Test
	@DisplayName("Find all foods and convert to option list")
	public void findOptions() {
		when(this.foodStampRepository.findAll()).thenReturn(this.foodStamps);

		List<OptionDTO<Long>> response = this.foodStampService.findOptions();

		assertTrue(this.options.size() == response.size() && this.options.containsAll(response));
	}

}
