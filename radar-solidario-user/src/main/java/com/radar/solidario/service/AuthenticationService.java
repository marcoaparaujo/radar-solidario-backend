package com.radar.solidario.service;

import com.radar.solidario.dto.authentication.AuthenticationFRPDTO;

public interface AuthenticationService {

	AuthenticationFRPDTO findById(Long id);

	AuthenticationFRPDTO findByEmail(String email);
}
