package com.radar.solidario.converter.user;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.radar.solidario.dto.user.UserPDTO;
import com.radar.solidario.entity.Authentication;
import com.radar.solidario.entity.User;

public class UserPDTOToAuthentication implements Converter<UserPDTO, Authentication> {

	@Override
	public Authentication convert(MappingContext<UserPDTO, Authentication> context) {
		UserPDTO source = context.getSource();

		User user = User.builder().name(source.getName()).birth(source.getBirth()).cell(source.getCell())
				.cpf(source.getCpf()).gender(source.getGender()).build();

		return Authentication.builder().email(source.getAuthentication().getEmail())
				.password(source.getAuthentication().getPassword()).user(user).build();
	}
}
