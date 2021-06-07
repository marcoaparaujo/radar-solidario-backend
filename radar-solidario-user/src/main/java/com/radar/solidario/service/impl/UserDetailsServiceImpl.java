package com.radar.solidario.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.radar.solidario.dto.authentication.AuthenticationFRPDTO;
import com.radar.solidario.security.entity.JwtUser;
import com.radar.solidario.service.AuthenticationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private AuthenticationService authenticationService;

	@Override
	public UserDetails loadUserByUsername(String email) {
		log.info("Start - UserDetailsServiceImpl.loadUserByUsername - Email: {}", email);

		AuthenticationFRPDTO authentication = this.authenticationService.findByEmail(email);
		JwtUser jwtUser = this.mapper.map(authentication, JwtUser.class);

		log.info("End - UserDetailsServiceImpl.loadUserByUsername - JwtUser: {}", jwtUser);
		return jwtUser;
	}
}
