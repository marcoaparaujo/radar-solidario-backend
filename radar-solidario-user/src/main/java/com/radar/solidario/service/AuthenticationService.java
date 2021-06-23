package com.radar.solidario.service;

import java.util.List;

import com.radar.solidario.dto.authentication.AuthenticationFPDTO;
import com.radar.solidario.dto.authentication.AuthenticationFRDTO;
import com.radar.solidario.dto.authentication.AuthenticationRDTO;
import com.radar.solidario.dto.user.UserPDTO;
import com.radar.solidario.entity.Role;

public interface AuthenticationService {

	AuthenticationFRDTO findById(Long id);

	AuthenticationFRDTO findByEmail(String email);
	
	AuthenticationRDTO include(UserPDTO userPDTO, List<Role> roles);
	
	AuthenticationRDTO edit(AuthenticationFPDTO authentication);

}
