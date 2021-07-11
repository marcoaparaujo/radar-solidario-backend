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
import com.radar.solidario.entity.User;
import com.radar.solidario.exception.user.alreadyExists.UserAlreadyExistsException;
import com.radar.solidario.exception.user.notFound.UserNotFoundException;
import com.radar.solidario.repository.UserRepository;

import properties.user.UserInstance;
import properties.user.UserProperties;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Processor - User")
public class UserProcessorTest extends UserProperties {

	@Mock
	private User user;

	@MockBean
	private UserRepository userRepository;

	@Autowired
	private UserProcessor userProcessor;

	@BeforeEach
	public void init() {
		this.user = UserInstance.instace();
	}

	@Test
	@DisplayName("Fetch a user by Id")
	public void exists() {
		when(this.userRepository.findById(ID)).thenReturn(Optional.of(this.user));

		User response = this.userProcessor.exists(ID);

		assertEquals(this.user, response);
		verify(this.userRepository, times(1)).findById(ID);
	}

	@Test
	@DisplayName("Fetch a non existent user by Id")
	public void existsNotFound() {
		when(this.userRepository.findById(WRONG_ID)).thenReturn(Optional.of(this.user));

		UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
			this.userProcessor.exists(ID);
		});

		assertEquals(ErrorCode.USER_NOT_FOUND.getMessage(), exception.getMessage());
	}

	@Test
	@DisplayName("Fetch a user by CPF")
	public void existsCpf() {
		when(this.userRepository.findByCpf(CPF)).thenReturn(Optional.of(this.user));

		User response = this.userProcessor.exists(CPF);

		assertEquals(this.user, response);
		verify(this.userRepository, times(1)).findByCpf(CPF);
	}

	@Test
	@DisplayName("Fetch a non existent user by CPF")
	public void existsCpfNotFound() {
		when(this.userRepository.findByCpf(WRONG_CPF)).thenReturn(Optional.of(this.user));

		UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
			this.userProcessor.exists(ID);
		});

		assertEquals(ErrorCode.USER_NOT_FOUND.getMessage(), exception.getMessage());
	}

	@Test
	@DisplayName("Check if user already exists by CPF")
	public void findByNisOrCpf() {
		this.userProcessor.alreadyExists(CPF);

		verify(this.userRepository, times(1)).findByCpf(CPF);
	}

	@Test
	@DisplayName("Check if user already exists by CPF with existent user")
	public void alreadyExists() {
		when(this.userRepository.findByCpf(CPF)).thenReturn(Optional.of(this.user));

		UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class, () -> {
			this.userProcessor.alreadyExists(CPF);
		});

		assertEquals(ErrorCode.USER_ALREADY_EXISTS.getMessage(), exception.getMessage());
	}
}
