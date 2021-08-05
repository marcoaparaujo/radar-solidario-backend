package com.radar.solidario.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.radar.solidario.entity.FoodStamp;

@Repository
public interface FoodStampRepository extends JpaRepository<FoodStamp, Long> {

	@Transactional(readOnly = true)
	List<FoodStamp> findAllByDate(LocalDate date);

	@Transactional(readOnly = true)
	List<FoodStamp> findAllByCharityName(String name);

	@Transactional(readOnly = true)
	Optional<FoodStamp> findByWeight(Double weight);
}
