package com.radar.solidario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.radar.solidario.entity.Donate;

@Repository
public interface DonateRepository extends JpaRepository<Donate, Long> {

	@Transactional(readOnly = true)
	@Query(value = "SELECT * FROM donate WHERE family_id = :id ORDER BY date DESC LIMIT 1", nativeQuery = true)
	Optional<Donate> findLastDonationById(@Param("id") Long id);
}
