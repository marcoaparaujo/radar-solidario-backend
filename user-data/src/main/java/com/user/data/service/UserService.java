package com.user.data.service;

import java.util.List;

import com.user.data.dto.user.UserRDTO;

public interface UserService {

	List<UserRDTO> findAllByName(String name);

	UserRDTO findByNis(String nis);

	void include(UserRDTO userRDTO);
}
