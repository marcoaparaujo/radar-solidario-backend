package com.radar.solidario.converter.authentication;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.radar.solidario.dto.authentication.AuthenticationFRDTO;
import com.radar.solidario.security.entity.JwtUser;

public class AuthenticationFRDTOToJwtUser implements Converter<AuthenticationFRDTO, JwtUser> {

	@Override
	public JwtUser convert(MappingContext<AuthenticationFRDTO, JwtUser> context) {
		AuthenticationFRDTO source = context.getSource();

		List<GrantedAuthority> authorities = source.getRole().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		
		return JwtUser.builder().id(source.getId()).email(source.getEmail()).password(source.getPassword())
				.authorities(authorities).build();
	}
}
