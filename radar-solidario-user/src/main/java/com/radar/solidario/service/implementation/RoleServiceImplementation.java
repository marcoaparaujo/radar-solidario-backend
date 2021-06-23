package com.radar.solidario.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.radar.solidario.dto.role.RoleFPDTO;
import com.radar.solidario.dto.role.RoleFRDTO;
import com.radar.solidario.dto.role.RoleHRDTO;
import com.radar.solidario.dto.role.RolePDTO;
import com.radar.solidario.dto.role.RoleRDTO;
import com.radar.solidario.entity.Role;
import com.radar.solidario.repository.RoleRepository;
import com.radar.solidario.service.RoleService;
import com.radar.solidario.service.processor.RoleProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RoleServiceImplementation implements RoleService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private RoleProcessor roleProcessor;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Page<RoleHRDTO> findAll(Pageable pageable) {
		log.info("Start - RoleServiceImplementation.findAll - Pageable: {}", pageable);

		Page<Role> roles = this.roleRepository.findAll(pageable);
		Page<RoleHRDTO> fltRoles = roles.map(role -> this.mapper.map(role, RoleHRDTO.class));

		log.info("End - RoleServiceImplementation.findAll - Page<RoleHRDTO>: {}", fltRoles);
		return fltRoles;
	}

	@Override
	public RoleFRDTO findById(Long id) {
		log.info("Start - RoleServiceImplementation.findById - Id: {}", id);

		Role role = this.roleProcessor.exists(id);
		RoleFRDTO roleFRDTO = this.mapper.map(role, RoleFRDTO.class);

		log.info("End - RoleServiceImplementation.findById - RoleFRDTO: {}", roleFRDTO);
		return roleFRDTO;
	}

	@Override
	public RoleFRDTO findByName(String name) {
		log.info("Start - RoleServiceImplementation.findByName - Name: {}", name);

		Role role = this.roleProcessor.exists(name);
		RoleFRDTO roleFRDTO = this.mapper.map(role, RoleFRDTO.class);

		log.info("End - RoleServiceImplementation.findByName - RoleFRDTO: {}", roleFRDTO);
		return roleFRDTO;
	}

	@Override
	public RoleRDTO include(RolePDTO rolePDTO) {
		log.info("Start - RoleServiceImplementation.include - RolePDTO: {}", rolePDTO);

		this.roleProcessor.alreadyExists(rolePDTO.getName());

		Role role = this.mapper.map(rolePDTO, Role.class);
		role = this.roleRepository.save(role);

		RoleRDTO roleRDTO = this.mapper.map(role, RoleRDTO.class);

		log.info("End - RoleServiceImplementation.include - RoleRDTO: {}", roleRDTO);
		return roleRDTO;
	}

	@Override
	public RoleFPDTO edit(RoleFPDTO roleFPDTO) {
		log.info("Start - RoleServiceImplementation.edit - RoleFPDTO: {}", roleFPDTO);

		Role role = this.mapper.map(roleFPDTO, Role.class);
		role = this.roleProcessor.merge(role);

		role = this.roleRepository.save(role);
		roleFPDTO = this.mapper.map(role, RoleFPDTO.class);

		log.info("End - RoleServiceImplementation.edit - RoleFPDTO: {}", roleFPDTO);
		return roleFPDTO;
	}

	@Override
	public RoleHRDTO remove(Long id) {
		log.info("Start - RoleServiceImplementation.remove - Id: {}", id);

		Role role = this.roleProcessor.exists(id);
		this.roleRepository.deleteById(id);

		RoleHRDTO roleHRDTO = this.mapper.map(role, RoleHRDTO.class);

		log.info("End - RoleServiceImplementation.remove - RoleHRDTO: {}", roleHRDTO);
		return roleHRDTO;
	}
}
