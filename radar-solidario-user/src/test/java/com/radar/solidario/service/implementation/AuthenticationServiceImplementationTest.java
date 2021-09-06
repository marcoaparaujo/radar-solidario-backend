package com.radar.solidario.service.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import com.radar.solidario.dto.authentication.AuthenticationFPDTO;
import com.radar.solidario.dto.authentication.AuthenticationFRDTO;
import com.radar.solidario.dto.authentication.AuthenticationRPDTO;
import com.radar.solidario.dto.user.UserPDTO;
import com.radar.solidario.entity.Authentication;
import com.radar.solidario.repository.AuthenticationRepository;
import com.radar.solidario.security.entity.JwtUser;
import com.radar.solidario.service.processor.AuthenticationProcessor;

import properties.authentication.AuthenticationInstance;
import properties.authentication.AuthenticationProperties;
import properties.security.SecurityInstance;
import properties.user.UserInstance;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Service Implementation - Authentication")
public class AuthenticationServiceImplementationTest extends AuthenticationProperties {

	@Mock
	private JwtUser jwtUser;

	@Mock
	private UserPDTO userPDTO;

	@Mock
	private Authentication authentication;

	@Mock
	private AuthenticationFRDTO authenticationFRDTO;

	@Mock
	private AuthenticationFPDTO authenticationFPDTO;

	@Mock
	private AuthenticationRPDTO authenticationRPDTO;

	@Spy
	private ModelMapper mapper;

	@Mock
	private AuthenticationProcessor authenticationProcessor;

	@Mock
	private AuthenticationRepository authenticationRepository;

	@InjectMocks
	private AuthenticationServiceImplementation authenticationService;

	@BeforeEach
	public void init() {
		this.mapper = new ModelMapper();

		this.userPDTO = UserInstance.instaceUserPDTO();
		this.jwtUser = SecurityInstance.instanceJwtUser();

		this.authentication = AuthenticationInstance.instace();
		this.authenticationFRDTO = AuthenticationInstance.instaceAuthenticationFRDTO();
		this.authenticationFPDTO = AuthenticationInstance.instanceAuthenticationFPDTO();
		this.authenticationRPDTO = AuthenticationInstance.instanceAuthenticationRPDTO();
	}

	@Test
	@DisplayName("Find an authentication by Id")
	public void findById() {
		when(this.authenticationProcessor.exists(ID)).thenReturn(this.authentication);

		AuthenticationFRDTO response = this.authenticationService.findById(ID);

		assertEquals(this.authenticationFRDTO, response);
	}

	@Test
	@DisplayName("Find an authentication by Email")
	public void findByEmail() {
		when(this.authenticationProcessor.exists(EMAIL)).thenReturn(this.authentication);

		AuthenticationFRDTO response = this.authenticationService.findByEmail(EMAIL);

		assertEquals(this.authenticationFRDTO, response);
	}

	@Test
	@DisplayName("Load an authentication by Username")
	public void loadUserByUsername() {
		when(this.authenticationProcessor.exists(EMAIL)).thenReturn(this.authentication);

		UserDetails response = this.authenticationService.loadUserByUsername(EMAIL);

		assertEquals(this.jwtUser.getPassword(), response.getPassword());
		assertEquals(this.jwtUser.getUsername(), response.getUsername());
	}
}
