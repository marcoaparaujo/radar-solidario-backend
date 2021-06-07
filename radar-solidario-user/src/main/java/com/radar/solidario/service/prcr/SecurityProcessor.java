package com.radar.solidario.service.prcr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.radar.solidario.entity.Authentication;
import com.radar.solidario.exception.authentication.locked.LockedAccountException;
import com.radar.solidario.exception.authentication.notFound.AuthenticationNotFoundException;
import com.radar.solidario.exception.authentication.wrongPassword.WrongPasswordException;
import com.radar.solidario.util.Encryptor;
import com.radar.solidario.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SecurityProcessor {

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private AuthenticationProcessor authenticationProcessor;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	public String authenticate(String email, String password) {
		log.info("Start - SecurityProcessor.authenticate - Email: {}, Password: -", email);

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,
				password);

		org.springframework.security.core.Authentication authenticate = this.authenticationManager
				.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authenticate);

		UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
		String token = this.jwtTokenUtil.create(userDetails);

		log.info("End - SecurityProcessor.authenticate - Token: {}", token);
		return token;
	}

	public Authentication check(String email) {
		log.info("Start - SecurityProcessor.check - Email: {}", email);

		Authentication authentication = this.authenticationProcessor.exists(email);
		this.wasEnabled(authentication);
		this.wasLocked(authentication);

		log.info("End - SecurityProcessor.check - Authentication: {}", authentication);
		return authentication;
	}

	public void wasEnabled(Authentication authentication) {
		log.info("Start - SecurityProcessor.wasEnabled - Authentication: {}", authentication);

		if (!authentication.getIsEnabled()) {
			log.error("AuthenticationNotFoundException - Email: {}", authentication.getEmail());
			throw new AuthenticationNotFoundException();
		}

		log.info("End - SecurityProcessor.wasEnabled");
	}

	public void wasLocked(Authentication authentication) {
		log.info("Start - SecurityProcessor.wasLocked - Authentication: {}", authentication);

		if (authentication.getIsLocked()) {
			log.error("LockedAccountException - Email: {}", authentication.getEmail());
			throw new LockedAccountException();
		}

		log.info("End - SecurityProcessor.wasLocked");
	}

	public void matchPassword(String password, String encodedPassword) {
		log.info("Start - SecurityProcessor.matchPassword - Password: -, Encoded Password: -");

		Boolean isSamePassword = Encryptor.match(password, encodedPassword);
		if (!isSamePassword) {
			log.error("WrongPasswordException - Password: -");
			throw new WrongPasswordException();
		}

		log.info("End - SecurityProcessor.matchPassword");
	}
}
