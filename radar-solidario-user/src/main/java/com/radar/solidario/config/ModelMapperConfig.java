package com.radar.solidario.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.radar.solidario.converter.authentication.AuthenticationFRDTOToJwtUser;
import com.radar.solidario.converter.authentication.AuthenticationToAuthenticationFRDTO;
import com.radar.solidario.converter.authentication.AuthenticationToUserRDTO;
import com.radar.solidario.converter.charity.CharityRPDTOToCharity;
import com.radar.solidario.converter.user.UserPIDTOToAuthentication;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		// Authentication
		modelMapper.addConverter(new AuthenticationToUserRDTO());
		modelMapper.addConverter(new AuthenticationFRDTOToJwtUser());
		modelMapper.addConverter(new AuthenticationToAuthenticationFRDTO());

		// Charity
		modelMapper.addConverter(new CharityRPDTOToCharity());

		// User
		modelMapper.addConverter(new UserPIDTOToAuthentication());

		return modelMapper;
	}
}