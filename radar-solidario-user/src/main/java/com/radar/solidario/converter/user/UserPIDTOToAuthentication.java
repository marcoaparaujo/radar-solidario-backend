package com.radar.solidario.converter.user;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.radar.solidario.dto.user.UserPIDTO;
import com.radar.solidario.entity.Authentication;
import com.radar.solidario.entity.User;

public class UserPIDTOToAuthentication implements Converter<UserPIDTO, Authentication> {

	@Override
	public Authentication convert(MappingContext<UserPIDTO, Authentication> context) {
		UserPIDTO source = context.getSource();

		User user = User.builder().name(source.getName()).birth(source.getBirth()).cell(source.getCell())
				.cpf(source.getCpf()).gender(source.getGender()).charity(source.getCharity()).build();

		return Authentication.builder().email(source.getAuthentication().getEmail())
				.password(source.getAuthentication().getPassword()).user(user).build();
	}
}
