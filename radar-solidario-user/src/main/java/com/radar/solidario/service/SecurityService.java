package com.radar.solidario.service;

import com.radar.solidario.dto.LoginDTO;
import com.radar.solidario.dto.token.TokenFRDTO;
import com.radar.solidario.dto.token.TokenRDTO;

public interface SecurityService {

	void lock(Long id);

	void unlock(Long id);

	TokenFRDTO login(LoginDTO loginDTO);

	TokenRDTO refresh(String token);
}
