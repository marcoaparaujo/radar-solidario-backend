package com.radar.solidario.service.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.radar.solidario.dto.LoginDTO;
import com.radar.solidario.dto.token.TokenFRDTO;
import com.radar.solidario.dto.token.TokenRDTO;
import com.radar.solidario.entity.Authentication;
import com.radar.solidario.repository.AuthenticationRepository;
import com.radar.solidario.service.processor.AuthenticationProcessor;
import com.radar.solidario.service.processor.SecurityProcessor;
import com.radar.solidario.util.JwtUtil;

import properties.authentication.AuthenticationInstance;
import properties.authentication.AuthenticationProperties;
import properties.security.SecurityInstance;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Service Implementation - Security")
public class SecurityServiceImplementationTest extends AuthenticationProperties {

	@Mock
	private LoginDTO loginDTO;

	@Mock
	private TokenRDTO tokenRDTO;

	@Mock
	private TokenFRDTO tokenFRDTO;

	@Mock
	private Authentication authentication;

	@Mock
	private JwtUtil jwtTokenUtil;

	@Mock
	private SecurityProcessor securityProcessor;

	@Mock
	private AuthenticationProcessor authenticationProcessor;

	@Mock
	private AuthenticationRepository authenticationRepository;

	@InjectMocks
	private SecurityServiceImplementation securityService;

	@BeforeEach
	public void init() {
		this.loginDTO = SecurityInstance.instanceLoginDTO();
		this.tokenRDTO = SecurityInstance.instanceTokenRDTO();
		this.authentication = AuthenticationInstance.instace();
		this.tokenFRDTO = SecurityInstance.instanceTokenFRDTO();

		when(this.authenticationRepository.save(any())).thenReturn(this.authentication);
	}

	@Test
	@DisplayName("Do login")
	public void login() {
		when(this.securityProcessor.check(EMAIL)).thenReturn(this.authentication);
		doNothing().when(this.securityProcessor).matchPassword(PASSWORD, PASSWORD);

		when(this.securityProcessor.authenticate(EMAIL, PASSWORD)).thenReturn(TOKEN);

		TokenFRDTO response = this.securityService.login(this.loginDTO);
		assertEquals(this.tokenFRDTO, response);
	}

	@Test
	@DisplayName("Refresh token")
	public void refresh() {
		when(this.jwtTokenUtil.refresh(INVALID_TOKEN_TYPE)).thenReturn(TOKEN);

		TokenRDTO response = this.securityService.refresh(TOKEN);

		assertEquals(this.tokenRDTO, response);
	}

	@Test
	@DisplayName("Lock an account")
	public void lock() {
		when(this.authenticationProcessor.exists(ID)).thenReturn(this.authentication);

		this.securityService.lock(ID);

		verify(this.authenticationProcessor, times(1)).exists(ID);
	}

	@Test
	@DisplayName("Unlock an account")
	public void unlock() {
		when(this.authenticationProcessor.exists(ID)).thenReturn(this.authentication);

		this.securityService.unlock(ID);

		verify(this.authenticationProcessor, times(1)).exists(ID);
	}
}
