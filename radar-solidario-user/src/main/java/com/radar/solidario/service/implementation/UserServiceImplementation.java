package com.radar.solidario.service.implementation;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radar.solidario.dto.LoginDTO;
import com.radar.solidario.dto.authentication.AuthenticationRPDTO;
import com.radar.solidario.dto.user.UserPDTO;
import com.radar.solidario.entity.Role;
import com.radar.solidario.service.AuthenticationService;
import com.radar.solidario.service.UserService;
import com.radar.solidario.service.processor.RoleProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private RoleProcessor roleProcessor;

	@Autowired
	private AuthenticationService authenticationService;

	@Override
	public LoginDTO includeVoluntary(UserPDTO userPDTO) {
		log.info("Start - UserServiceImplementation.include - UserPDTO: {}", userPDTO);

		Role role = this.roleProcessor.exists("VOLUNTARY");
		List<Role> roles = Arrays.asList(role);

		AuthenticationRPDTO authenticationRDTO = this.authenticationService.include(userPDTO, roles);
		LoginDTO loginDTO = this.mapper.map(authenticationRDTO, LoginDTO.class);

		log.info("End - UserServiceImplementation.include - LoginDTO: {}", loginDTO);
		return loginDTO;
	}
}
