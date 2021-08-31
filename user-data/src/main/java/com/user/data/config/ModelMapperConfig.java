package com.user.data.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.user.data.converter.user.UserToUserRDTO;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		// User
		modelMapper.addConverter(new UserToUserRDTO());

		return modelMapper;
	}
}