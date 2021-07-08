package com.radar.solidario.service;

import java.util.List;

import com.radar.solidario.constant.AuthenticationRole;
import com.radar.solidario.dto.authentication.AuthenticationFPDTO;
import com.radar.solidario.dto.authentication.AuthenticationFRDTO;
import com.radar.solidario.dto.authentication.AuthenticationRPDTO;
import com.radar.solidario.dto.user.UserPDTO;

public interface AuthenticationService {

	AuthenticationFRDTO findById(Long id);

	AuthenticationFRDTO findByEmail(String email);

	AuthenticationRPDTO include(UserPDTO userPDTO, List<AuthenticationRole> roles);

	AuthenticationRPDTO edit(AuthenticationFPDTO authentication);

}
