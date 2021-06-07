package com.radar.solidario.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radar.solidario.dto.authentication.AuthenticationFRPDTO;
import com.radar.solidario.entity.Authentication;
import com.radar.solidario.service.AuthenticationService;
import com.radar.solidario.service.prcr.AuthenticationProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private AuthenticationProcessor authenticationProcessor;

	@Override
	public AuthenticationFRPDTO findById(Long id) {
		log.info("Start - AuthenticationServiceImpl.findById - Id: {}", id);

		Authentication authentication = this.authenticationProcessor.exists(id);
		AuthenticationFRPDTO authenticationFRPDTO = this.mapper.map(authentication, AuthenticationFRPDTO.class);

		log.info("End - AuthenticationServiceImpl.findById - AuthenticationFRPDTO: {}", authenticationFRPDTO);
		return authenticationFRPDTO;
	}

	@Override
	public AuthenticationFRPDTO findByEmail(String email) {
		log.info("Start - AuthenticationServiceImpl.findByEmail - Email: {}", email);

		Authentication authentication = this.authenticationProcessor.exists(email);
		AuthenticationFRPDTO authenticationFRPDTO = this.mapper.map(authentication, AuthenticationFRPDTO.class);

		log.info("End - AuthenticationServiceImpl.findByEmail - AuthenticationFRPDTO: {}", authenticationFRPDTO);
		return authenticationFRPDTO;
	}
}
