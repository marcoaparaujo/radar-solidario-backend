package com.user.data.converter.user;

import java.time.LocalDate;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.user.data.dto.user.UserRDTO;
import com.user.data.entity.User;

public class UserToUserRDTO implements Converter<User, UserRDTO> {

	@Override
	public UserRDTO convert(MappingContext<User, UserRDTO> context) {
		User source = context.getSource();

		return UserRDTO.builder().name(source.getName()).nis(source.getNis()).birth(LocalDate.parse(source.getBirth()))
				.build();
	}
}
