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
import com.radar.solidario.entity.Authentication;
import com.radar.solidario.exception.authentication.alreadyExists.AuthenticationAlreadyExistsException;
import com.radar.solidario.exception.authentication.notChanged.AuthenticationNotChangedException;
import com.radar.solidario.exception.authentication.notFound.AuthenticationNotFoundException;
import com.radar.solidario.repository.AuthenticationRepository;

import properties.authentication.AuthenticationInstance;
import properties.authentication.AuthenticationProperties;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Processor - Authentication")
public class AuthenticationProcessorTest extends AuthenticationProperties {

	@Mock
	private Authentication authentication;

	@MockBean
	private AuthenticationRepository authenticationRepository;

	@Autowired
	private AuthenticationProcessor authenticationProcessor;

	@BeforeEach
	public void init() {
		this.authentication = AuthenticationInstance.instace();
	}

	@Test
	@DisplayName("Fetch an authentication")
	public void exists() {
		when(this.authenticationRepository.findById(ID)).thenReturn(Optional.of(this.authentication));

		Authentication response = this.authenticationProcessor.exists(ID);

		assertEquals(this.authentication, response);
		verify(this.authenticationRepository, times(1)).findById(ID);
	}

	@Test
	@DisplayName("Fetch a non existent authentication")
	public void existsNotFound() {
		when(this.authenticationRepository.findById(WRONG_ID)).thenReturn(Optional.of(this.authentication));

		AuthenticationNotFoundException exception = assertThrows(AuthenticationNotFoundException.class, () -> {
			this.authenticationProcessor.exists(ID);
		});

		assertEquals(ErrorCode.AUTHENTICATION_NOT_FOUND.getMessage(), exception.getMessage());
	}

	@Test
	@DisplayName("Fetch an authentication by email")
	public void existsEmail() {
		when(this.authenticationRepository.findByEmail(EMAIL)).thenReturn(Optional.of(this.authentication));

		Authentication response = this.authenticationProcessor.exists(EMAIL);

		assertEquals(this.authentication, response);
		verify(this.authenticationRepository, times(1)).findByEmail(EMAIL);
	}

	@Test
	@DisplayName("Fetch a non existent authentication by email")
	public void existsEmailNotFound() {
		when(this.authenticationRepository.findByEmail(WRONG_EMAIL)).thenReturn(Optional.of(this.authentication));

		AuthenticationNotFoundException exception = assertThrows(AuthenticationNotFoundException.class, () -> {
			this.authenticationProcessor.exists(EMAIL);
		});

		assertEquals(ErrorCode.AUTHENTICATION_NOT_FOUND.getMessage(), exception.getMessage());
	}

	@Test
	@DisplayName("Check if authentication already exists")
	public void alreadyExists() {
		when(this.authenticationRepository.findByEmail(EMAIL)).thenReturn(Optional.empty());

		this.authenticationProcessor.alreadyExists(EMAIL);
		verify(this.authenticationRepository, times(1)).findByEmail(EMAIL);
	}

	@Test
	@DisplayName("Check if authentication already exists with an existent authentication")
	public void alreadyExistsAuthenticationAlreadyExistsException() {
		when(this.authenticationRepository.findByEmail(EMAIL)).thenReturn(Optional.of(this.authentication));

		AuthenticationAlreadyExistsException exception = assertThrows(AuthenticationAlreadyExistsException.class,
				() -> {
					this.authenticationProcessor.alreadyExists(EMAIL);
				});

		assertEquals(ErrorCode.AUTHENTICATION_ALREADY_EXISTS.getMessage(), exception.getMessage());
	}

	@Test
	@DisplayName("Merge authentications")
	public void merge() {
		Authentication wrongAuthentication = AuthenticationInstance.instace();
		wrongAuthentication.setEmail(WRONG_EMAIL);
		
		when(this.authenticationRepository.findById(ID)).thenReturn(Optional.of(wrongAuthentication));

		Authentication response = this.authenticationProcessor.merge(this.authentication);
		this.authentication.setPassword(PASSWORD);

		assertEquals(this.authentication, response);
	}

	@Test
	@DisplayName("Merge authentications with equals object")
	public void mergeNotChangedException() {
		when(this.authenticationRepository.findById(ID)).thenReturn(Optional.of(this.authentication));

		AuthenticationNotChangedException exception = assertThrows(AuthenticationNotChangedException.class, () -> {
			this.authenticationProcessor.merge(this.authentication);
		});

		assertEquals(ErrorCode.AUTHENTICATION_NOT_CHANGED.getMessage(), exception.getMessage());
	}
}
