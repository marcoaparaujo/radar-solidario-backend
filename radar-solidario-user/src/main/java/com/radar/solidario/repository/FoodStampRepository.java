package com.radar.solidario.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.radar.solidario.entity.FoodStamp;

@Repository
public interface FoodStampRepository extends JpaRepository<FoodStamp, Long> {
	
	Page<FoodStamp> findAll(Pageable pageable);
	
	List<FoodStamp> findAllByDate(LocalDate date);

	List<FoodStamp> findAllByCharityName(String name);
	
	Page<FoodStamp> findAllByIsAble(Pageable pageable, Boolean isAble);

	Optional<FoodStamp> findByWeight(Double weight);
}
