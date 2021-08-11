package com.radar.solidario.converter.authentication;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.radar.solidario.dto.authentication.AuthenticationRPDTO;
import com.radar.solidario.dto.user.UserRDTO;
import com.radar.solidario.entity.Authentication;

public class AuthenticationToUserRDTO implements Converter<Authentication, UserRDTO> {

	@Override
	public UserRDTO convert(MappingContext<Authentication, UserRDTO> context) {
		Authentication source = context.getSource();

		AuthenticationRPDTO authentication = AuthenticationRPDTO.builder().email(source.getEmail())
				.password(source.getPassword()).build();

		return UserRDTO.builder().name(source.getUser().getName()).birth(source.getUser().getBirth())
				.cell(source.getUser().getCell()).cpf(source.getUser().getCpf()).authentication(authentication).build();
	}
}
