package com.radar.solidario.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.radar.solidario.dto.role.RoleFPDTO;
import com.radar.solidario.dto.role.RoleFRDTO;
import com.radar.solidario.dto.role.RoleHRDTO;
import com.radar.solidario.dto.role.RolePDTO;
import com.radar.solidario.dto.role.RoleRDTO;

public interface RoleService {

	Page<RoleHRDTO> findAll(Pageable pageable);

	RoleFRDTO findById(Long id);

	RoleFRDTO findByName(String name);

	RoleRDTO include(RolePDTO rolePDTO);

	RoleFPDTO edit(RoleFPDTO roleFPDTO);

	RoleHRDTO remove(Long id);
}
