package com.radar.solidario.service.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.radar.solidario.constant.ErrorCode;
import com.radar.solidario.entity.Charity;
import com.radar.solidario.exception.charity.alreadyExists.CharityAlreadyExistsException;
import com.radar.solidario.exception.charity.notFound.CharityNotFoundException;
import com.radar.solidario.repository.CharityRepository;

import properties.charity.CharityInstance;
import properties.charity.CharityProperties;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Processor - Charity")
public class CharityProcessorTest extends CharityProperties {

	@Mock
	private Charity charity;

	@MockBean
	private CharityRepository charityRepository;

	@Autowired
	private CharityProcessor charityProcessor;

	@BeforeEach
	public void init() {
		this.charity = CharityInstance.instace();
	}

	@Test
	@DisplayName("Fetch a charity")
	void exists() {
		when(this.charityRepository.findById(ID)).thenReturn(Optional.of(this.charity));

		Charity response = this.charityProcessor.exists(ID);

		assertEquals(this.charity, response);
		verify(this.charityRepository, times(1)).findById(this.charity.getId());
	}

	@Test
	@DisplayName("Fetch a existsNotFound charity")
	void existsNotFound() {
		when(this.charityRepository.findById(ID)).thenReturn(Optional.empty());

		CharityNotFoundException exception = assertThrows(CharityNotFoundException.class, () -> {
			this.charityProcessor.exists(ID);
		});

		assertEquals(ErrorCode.CHARITY_NOT_FOUND.getMessage(), exception.getMessage());
		verify(this.charityRepository, times(1)).findById(ID);
	}

	@Test
	@DisplayName("Fetch a charity by NAME")
	void existsName() {
		when(this.charityRepository.findByName(NAME)).thenReturn(Optional.of(this.charity));

		Charity response = this.charityProcessor.exists(NAME);

		assertEquals(this.charity, response);
		verify(this.charityRepository, times(1)).findByName(NAME);
	}

	@Test
	@DisplayName("Fetch a existsNotFound charity by NAME")
	void existsNameNotFound() {
		when(this.charityRepository.findByName(NAME)).thenReturn(Optional.empty());

		CharityNotFoundException exception = assertThrows(CharityNotFoundException.class, () -> {
			this.charityProcessor.exists(NAME);
		});

		assertEquals(ErrorCode.CHARITY_NOT_FOUND.getMessage(), exception.getMessage());
		verify(this.charityRepository, times(1)).findByName(NAME);
	}

	@Test
	@DisplayName("Check if charity already exists with an existent charity")
	void alreadyExists() {
		when(this.charityRepository.findByName(NAME)).thenReturn(Optional.of(this.charity));

		CharityAlreadyExistsException exception = assertThrows(CharityAlreadyExistsException.class, () -> {
			this.charityProcessor.alreadyExists(NAME);
		});

		assertEquals(ErrorCode.CHARITY_ALREADY_EXISTS.getMessage(), exception.getMessage());
	}

}
