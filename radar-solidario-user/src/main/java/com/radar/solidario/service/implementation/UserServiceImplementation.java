package com.radar.solidario.service.implementation;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radar.solidario.constant.AuthenticationRole;
import com.radar.solidario.dto.LoginDTO;
import com.radar.solidario.dto.authentication.AuthenticationRPDTO;
import com.radar.solidario.dto.user.UserPDTO;
import com.radar.solidario.service.AuthenticationService;
import com.radar.solidario.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private AuthenticationService authenticationService;

	@Override
	public LoginDTO includeVoluntary(UserPDTO userPDTO) {
		log.info("Start - UserServiceImplementation.include - UserPDTO: {}", userPDTO);

		List<AuthenticationRole> roles = Arrays.asList(AuthenticationRole.VOLUNTARY);

		AuthenticationRPDTO authenticationRDTO = this.authenticationService.include(userPDTO, roles);
		LoginDTO loginDTO = this.mapper.map(authenticationRDTO, LoginDTO.class);

		log.info("End - UserServiceImplementation.include - LoginDTO: {}", loginDTO);
		return loginDTO;
	}
}
