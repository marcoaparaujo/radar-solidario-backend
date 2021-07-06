package com.radar.solidario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.radar.solidario.entity.Family;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Long> {

	@Transactional(readOnly = true)
	Optional<Family> findByNis(String nis);

	@Transactional(readOnly = true)
	Optional<Family> findByCpf(String cpf);
}
