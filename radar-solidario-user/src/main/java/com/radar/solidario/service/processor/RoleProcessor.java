package com.radar.solidario.service.processor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.radar.solidario.entity.Role;
import com.radar.solidario.exception.role.alreadyExists.RoleAlreadyExistsException;
import com.radar.solidario.exception.role.notFound.RoleNotFoundException;
import com.radar.solidario.repository.RoleRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RoleProcessor {

	@Autowired
	private RoleRepository roleRepository;

	public Role exists(Long id) {
		log.info("Start - RoleProcessor.exists - Id: {}", id);

		Optional<Role> optRole = this.roleRepository.findById(id);
		if (optRole.isEmpty()) {
			log.error("RoleNotFoundException - Id: {}", id);
			throw new RoleNotFoundException();
		}

		log.info("End - RoleProcessor.exists - Role: {}", optRole.get());
		return optRole.get();
	}

	public Role exists(String name) {
		log.info("Start - RoleProcessor.exists - Name: {}", name);

		Optional<Role> optRole = this.roleRepository.findByName(name);
		if (optRole.isEmpty()) {
			log.error("RoleNotFoundException - Name: {}", name);
			throw new RoleNotFoundException();
		}

		log.info("End - RoleProcessor.exists - Role: {}", optRole.get());
		return optRole.get();
	}

	public void alreadyExists(String name) {
		log.info("Start - RoleProcessor.alreadyExists - Name: {}", name);

		Optional<Role> optRole = this.roleRepository.findByName(name);
		if (optRole.isPresent()) {
			log.error("RoleAlreadyExistsException - Name: {}", name);
			throw new RoleAlreadyExistsException();
		}

		log.info("End - RoleProcessor.alreadyExists");
	}

	public Role merge(Role role) {
		log.info("Start - RoleProcessor.append - Role: {}", role);

		Role original = this.exists(role.getId());
		role.setAuthentication(original.getAuthentication());

		log.info("End - RoleProcessor.append - Role: {}", role);
		return role;
	}
}
