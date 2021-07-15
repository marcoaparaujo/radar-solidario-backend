package com.radar.solidario.service.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;

import com.radar.solidario.constant.ErrorCode;
import com.radar.solidario.entity.Authentication;
import com.radar.solidario.exception.authentication.locked.LockedAccountException;
import com.radar.solidario.exception.authentication.wrongPassword.WrongPasswordException;
import com.radar.solidario.repository.AuthenticationRepository;
import com.radar.solidario.service.AuthenticationService;
import com.radar.solidario.util.Encryptor;
import com.radar.solidario.util.JwtUtil;

import properties.authentication.AuthenticationInstance;
import properties.authentication.AuthenticationProperties;
import properties.security.SecurityInstance;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Processor - Security")
public class SecurityProcessorTest extends AuthenticationProperties {

	@Mock
	private UserDetails userDetails;

	@Mock
	private Authentication authentication;

	@Mock
	private UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;

	@MockBean
	private JwtUtil jwtTokenUtil;

	@MockBean
	private AuthenticationProcessor authenticationProcessor;

	@MockBean
	private UserDetailsService userDetailsService;

	@MockBean
	private AuthenticationService authenticationService;

	@MockBean
	private AuthenticationManager authenticationManager;

	@MockBean
	private AuthenticationRepository authenticationRepository;

	@Autowired
	private SecurityProcessor securityProcessor;

	@BeforeEach
	public void init() {
		this.userDetails = SecurityInstance.jwtUserInstance();
		this.authentication = AuthenticationInstance.instace();
		this.usernamePasswordAuthenticationToken = SecurityInstance.usernamePasswordAuthenticationTokenInstance();
	}

	@Test
	@DisplayName("Do a login with valid user")
	public void authenticate() {
		when(this.jwtTokenUtil.create(this.userDetails)).thenReturn(TOKEN);
		when(this.userDetailsService.loadUserByUsername(EMAIL)).thenReturn(this.userDetails);
		when(this.authenticationManager.authenticate(this.usernamePasswordAuthenticationToken)).thenReturn(any());

		String response = this.securityProcessor.authenticate(EMAIL, PASSWORD);
		assertEquals(TOKEN, response);
	}

	@Test
	@DisplayName("Check if account was locked")
	public void check() {
		when(this.authenticationProcessor.exists(EMAIL)).thenReturn(this.authentication);

		Authentication response = this.securityProcessor.check(EMAIL);
		assertEquals(this.authentication, response);
	}

	@Test
	@DisplayName("Check if account was locked with locked account")
	public void wasLockedLockedAccountException() {
		this.authentication.setIsLocked(true);

		LockedAccountException exception = assertThrows(LockedAccountException.class, () -> {
			this.securityProcessor.wasLocked(this.authentication);
		});

		assertEquals(ErrorCode.LOCKED_ACCOUNT.getMessage(), exception.getMessage());
	}

	@Test
	@DisplayName("Check if password is match")
	public void matchPassword() {
		String encoded = Encryptor.encode(PASSWORD);

		MockedStatic<Encryptor> encryptorMock = mockStatic(Encryptor.class);
		encryptorMock.when(() -> Encryptor.match(PASSWORD, encoded)).thenReturn(true);

		this.securityProcessor.matchPassword(PASSWORD, encoded);
		encryptorMock.verify(() -> Encryptor.match(PASSWORD, encoded));
	}

	@Test
	@DisplayName("Check if password is match with a wrong password")
	public void matchPasswordWrongPasswordException() {
		WrongPasswordException exception = assertThrows(WrongPasswordException.class, () -> {
			this.securityProcessor.matchPassword(PASSWORD, WRONG_PASSWORD);
		});

		assertEquals(ErrorCode.WRONG_PASSWORD.getMessage(), exception.getMessage());
	}
}
