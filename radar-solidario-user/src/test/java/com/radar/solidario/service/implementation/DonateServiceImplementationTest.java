package com.radar.solidario.service.implementation;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.radar.solidario.dto.donate.DonatePDTO;
import com.radar.solidario.dto.donate.DonateRDTO;
import com.radar.solidario.dto.foodStamp.FoodStampHPDTO;
import com.radar.solidario.entity.Charity;
import com.radar.solidario.entity.Donate;
import com.radar.solidario.entity.Family;
import com.radar.solidario.entity.FoodStamp;
import com.radar.solidario.entity.User;
import com.radar.solidario.repository.DonateRepository;
import com.radar.solidario.service.processor.CharityProcessor;
import com.radar.solidario.service.processor.DonateProcessor;
import com.radar.solidario.service.processor.FamilyProcessor;
import com.radar.solidario.service.processor.FoodStampProcessor;
import com.radar.solidario.service.processor.UserProcessor;

import properties.charity.CharityInstance;
import properties.donate.DonateInstance;
import properties.donate.DonateProperties;
import properties.family.FamilyInstance;
import properties.foodStamp.FoodStampInstance;
import properties.user.UserInstance;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Service Implementation - Donate")
public class DonateServiceImplementationTest extends DonateProperties {

	@Mock
	private Donate donate;

	@Mock
	private User user;

	@Mock
	private Family family;

	@Mock
	private Charity charity;

	@Mock
	private FoodStamp foodStamp;

	@Mock
	private List<FoodStampHPDTO> foodStampHPDTO;

	@Mock
	private DonatePDTO donatePDTO;

	@Mock
	private DonateRDTO donateRDTO;

	@Spy
	private ModelMapper mapper;

	@Mock
	private DonateProcessor donateProcessor;

	@Mock
	private UserProcessor userProcessor;

	@Mock
	private FamilyProcessor familyProcessor;

	@Mock
	private CharityProcessor charityProcessor;

	@Mock
	private FoodStampProcessor foodStampProcessor;

	@Mock
	private DonateRepository donateRepository;

	@InjectMocks
	private DonateServiceImplementation donateService;

	@BeforeEach
	public void init() {
		this.mapper = new ModelMapper();

		this.donate = DonateInstance.instace();
		this.donatePDTO = DonateInstance.instacePDTO();
		this.donateRDTO = DonateInstance.instaceRDTO();

		this.user = UserInstance.instace();
		this.family = FamilyInstance.instace();
		this.charity = CharityInstance.instace();
		this.foodStamp = FoodStampInstance.instace();
		this.foodStampHPDTO = FoodStampInstance.instaceFoodStampsHPDTOs(5);
	}

	@Test
	@DisplayName("Find a donate by ID")
	public void findById() {
		when(this.donateProcessor.exists(ID)).thenReturn(this.donate);

		DonateRDTO response = this.donateService.findById(ID);

		assertEquals(this.donateRDTO, response);
	}

	@Test
	@DisplayName("Make donation")
	public void donate() {
		when(this.userProcessor.exists(ID)).thenReturn(this.user);
		when(this.familyProcessor.exists(ID)).thenReturn(this.family);
		when(this.charityProcessor.exists(ID)).thenReturn(this.charity);
		when(this.foodStampProcessor.exists(ID)).thenReturn(this.foodStamp);
		when(this.donateRepository.save(any())).thenReturn(this.donate);

		DonateRDTO response = this.donateService.donate(donatePDTO);

		assertEquals(this.donateRDTO, response);

	}

}
