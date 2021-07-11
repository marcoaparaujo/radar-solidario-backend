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
import com.radar.solidario.entity.Family;
import com.radar.solidario.exception.family.alreadyExists.FamilyAlreadyExistsException;
import com.radar.solidario.exception.family.notFound.FamilyNotFoundException;
import com.radar.solidario.repository.FamilyRepository;

import properties.family.FamilyInstance;
import properties.family.FamilyProperties;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Processor - Family")
public class FamilyProcessorTest extends FamilyProperties {

	@Mock
	private Family family;

	@MockBean
	private FamilyRepository familyRepository;

	@Autowired
	private FamilyProcessor familyProcessor;

	@BeforeEach
	public void init() {
		this.family = FamilyInstance.instace();
	}

	@Test
	@DisplayName("Fetch a family by Id")
	public void exists() {
		when(this.familyRepository.findById(ID)).thenReturn(Optional.of(this.family));

		Family response = this.familyProcessor.exists(ID);

		assertEquals(this.family, response);
		verify(this.familyRepository, times(1)).findById(ID);
	}

	@Test
	@DisplayName("Fetch a non existent family by Id")
	public void existsNotFound() {
		when(this.familyRepository.findById(WRONG_ID)).thenReturn(Optional.of(this.family));

		FamilyNotFoundException exception = assertThrows(FamilyNotFoundException.class, () -> {
			this.familyProcessor.exists(ID);
		});

		assertEquals(ErrorCode.FAMILY_NOT_FOUND.getMessage(), exception.getMessage());
		verify(this.familyRepository, times(1)).findById(ID);
	}

	@Test
	@DisplayName("Fetch a family by CPF")
	public void existsCpf() {
		when(this.familyRepository.findByCpf(CPF)).thenReturn(Optional.of(this.family));

		Family response = this.familyProcessor.existsCpf(CPF);

		assertEquals(this.family, response);
		verify(this.familyRepository, times(1)).findByCpf(CPF);
	}

	@Test
	@DisplayName("Fetch a non existent family by CPF")
	public void existsCpfNotFound() {
		when(this.familyRepository.findByCpf(WRONG_CPF)).thenReturn(Optional.of(this.family));

		FamilyNotFoundException exception = assertThrows(FamilyNotFoundException.class, () -> {
			this.familyProcessor.existsCpf(CPF);
		});

		assertEquals(ErrorCode.FAMILY_NOT_FOUND.getMessage(), exception.getMessage());
		verify(this.familyRepository, times(1)).findByCpf(CPF);
	}

	@Test
	@DisplayName("Fetch a family by NIS")
	public void existsNis() {
		when(this.familyRepository.findByNis(NIS)).thenReturn(Optional.of(this.family));

		Family response = this.familyProcessor.existsNis(NIS);

		assertEquals(this.family, response);
		verify(this.familyRepository, times(1)).findByNis(NIS);
	}

	@Test
	@DisplayName("Fetch a non existent family by NIS")
	public void existsNisNotFound() {
		when(this.familyRepository.findByNis(WRONG_NIS)).thenReturn(Optional.of(this.family));

		FamilyNotFoundException exception = assertThrows(FamilyNotFoundException.class, () -> {
			this.familyProcessor.existsNis(NIS);
		});

		assertEquals(ErrorCode.FAMILY_NOT_FOUND.getMessage(), exception.getMessage());
		verify(this.familyRepository, times(1)).findByNis(NIS);
	}

	@Test
	@DisplayName("Fetch a family by NIS or CPF")
	public void findByNisOrCpf() {
		this.familyProcessor.alreadyExists(NIS, CPF);

		verify(this.familyRepository, times(1)).findByNisOrCpf(NIS, CPF);
	}

	@Test
	@DisplayName("Fetch a already existent family by NIS or CPF")
	public void alreadyExists() {
		when(this.familyRepository.findByNisOrCpf(NIS, CPF)).thenReturn(Optional.of(this.family));

		FamilyAlreadyExistsException exception = assertThrows(FamilyAlreadyExistsException.class, () -> {
			this.familyProcessor.alreadyExists(NIS, CPF);
		});

		assertEquals(ErrorCode.FAMILY_ALREADY_EXISTS.getMessage(), exception.getMessage());
	}
}
