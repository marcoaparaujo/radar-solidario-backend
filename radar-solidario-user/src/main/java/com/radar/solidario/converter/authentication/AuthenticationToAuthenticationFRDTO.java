package com.radar.solidario.converter.authentication;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.radar.solidario.dto.authentication.AuthenticationFRDTO;
import com.radar.solidario.dto.user.UserHRDTO;
import com.radar.solidario.entity.Authentication;

public class AuthenticationToAuthenticationFRDTO implements Converter<Authentication, AuthenticationFRDTO> {

	@Override
	public AuthenticationFRDTO convert(MappingContext<Authentication, AuthenticationFRDTO> context) {
		Authentication source = context.getSource();

		UserHRDTO user = UserHRDTO.builder().name(source.getUser().getName()).birth(source.getUser().getBirth())
				.cell(source.getUser().getCell()).cpf(source.getUser().getCpf()).build();

		return AuthenticationFRDTO.builder().id(source.getId()).email(source.getEmail()).password(source.getPassword())
				.isLocked(source.getIsLocked()).role(source.getRole()).user(user).build();
	}
}
