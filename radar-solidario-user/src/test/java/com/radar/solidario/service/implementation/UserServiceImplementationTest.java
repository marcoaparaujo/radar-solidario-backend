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

import com.radar.solidario.dto.LoginDTO;
import com.radar.solidario.dto.authentication.AuthenticationRPDTO;
import com.radar.solidario.dto.user.UserPDTO;
import com.radar.solidario.service.processor.UserProcessor;

import properties.authentication.AuthenticationInstance;
import properties.family.FamilyProperties;
import properties.security.SecurityInstance;
import properties.user.UserInstance;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Service Implementation - User")
public class UserServiceImplementationTest extends FamilyProperties {

	@Mock
	private UserPDTO userPDTO;

	@Mock
	private LoginDTO loginDTO;

	@Mock
	private AuthenticationRPDTO authenticationRPDTO;

	@Spy
	private ModelMapper mapper;

	@Mock
	private UserProcessor userProcessor;

	@Mock
	private AuthenticationServiceImplementation authenticationService;

	@InjectMocks
	private UserServiceImplementation userServiceImplementation;

	@BeforeEach
	public void init() {
		this.mapper = new ModelMapper();

		this.userPDTO = UserInstance.instaceUserPDTO();
		this.loginDTO = SecurityInstance.instanceLoginDTO();
		this.authenticationRPDTO = AuthenticationInstance.instanceAuthenticationRPDTO();
	}

	@Test
	@DisplayName("Include an user with voluntary role")
	public void includeVoluntary() {
		when(this.authenticationService.include(any(), any())).thenReturn(this.authenticationRPDTO);

		LoginDTO response = this.userServiceImplementation.includeVoluntary(this.userPDTO);

		assertEquals(this.loginDTO, response);
	}
}
