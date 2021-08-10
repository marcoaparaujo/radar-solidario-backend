package com.radar.solidario.service;

import com.radar.solidario.dto.LoginDTO;
import com.radar.solidario.dto.user.UserPDTO;

public interface UserService {

	LoginDTO includeVoluntary(UserPDTO userPDTO);
	
	LoginDTO includeAdministrator(UserPDTO userPDTO);
}
