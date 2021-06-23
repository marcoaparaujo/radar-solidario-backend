package com.radar.solidario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.radar.solidario.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	@Transactional(readOnly = true)
	Optional<Role> findByName(String name);
}
