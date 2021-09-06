package com.radar.solidario.service.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
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
import com.radar.solidario.entity.Donate;
import com.radar.solidario.exception.donate.notFound.DonateNotFoundException;
import com.radar.solidario.repository.DonateRepository;

import properties.charity.CharityProperties;
import properties.donate.DonateInstance;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Processor - Charity")
public class DonateProcessorTest extends CharityProperties {

	@Mock
	private Donate donate;

	@Mock
	private List<Donate> donates;

	@MockBean
	private DonateRepository donateRepository;

	@Autowired
	private DonateProcessor donateProcessor;

	@BeforeEach
	public void init() {
		this.donate = DonateInstance.instace();
		this.donates = DonateInstance.instaceDonates(5);

	}

	@Test
	@DisplayName("Fetch a donate")
	void exists() {
		when(this.donateRepository.findById(ID)).thenReturn(Optional.of(this.donate));

		Donate response = this.donateProcessor.exists(ID);

		assertEquals(this.donate, response);
		verify(this.donateRepository, times(1)).findById(this.donate.getId());
	}

	@Test
	@DisplayName("Fetch a existsNotFound donate")
	void existsNotFound() {
		when(this.donateRepository.findById(ID)).thenReturn(Optional.empty());

		DonateNotFoundException exception = assertThrows(DonateNotFoundException.class, () -> {
			this.donateProcessor.exists(ID);
		});

		assertEquals(ErrorCode.DONATE_NOT_FOUND.getMessage(), exception.getMessage());
		verify(this.donateRepository, times(1)).findById(ID);
	}

}
