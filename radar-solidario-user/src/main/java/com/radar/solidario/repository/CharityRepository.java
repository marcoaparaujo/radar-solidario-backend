package com.radar.solidario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.radar.solidario.entity.Charity;

@Repository
public interface CharityRepository extends JpaRepository<Charity, Long> {

	@Transactional(readOnly = true)
	Optional<Charity> findByName(String name);

	

}
