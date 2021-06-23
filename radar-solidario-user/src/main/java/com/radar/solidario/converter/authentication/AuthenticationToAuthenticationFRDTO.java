package com.radar.solidario.converter.authentication;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.radar.solidario.dto.authentication.AuthenticationFRDTO;
import com.radar.solidario.dto.role.RoleHRDTO;
import com.radar.solidario.dto.user.UserHRDTO;
import com.radar.solidario.entity.Authentication;

public class AuthenticationToAuthenticationFRDTO implements Converter<Authentication, AuthenticationFRDTO> {

	@Override
	public AuthenticationFRDTO convert(MappingContext<Authentication, AuthenticationFRDTO> context) {
		Authentication source = context.getSource();

		List<RoleHRDTO> roles = source.getRole().stream().map(role -> RoleHRDTO.builder().name(role.getName()).build())
				.collect(Collectors.toList());

		UserHRDTO user = UserHRDTO.builder().name(source.getUser().getName()).birth(source.getUser().getBirth())
				.cell(source.getUser().getCell()).cpf(source.getUser().getCpf()).gender(source.getUser().getGender())
				.build();

		return AuthenticationFRDTO.builder().id(source.getId()).email(source.getEmail()).password(source.getPassword())
				.isLocked(source.getIsLocked()).role(roles).user(user).build();
	}
}
