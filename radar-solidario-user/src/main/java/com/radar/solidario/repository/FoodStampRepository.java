package com.radar.solidario.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.radar.solidario.entity.FoodStamp;

@Repository
public interface FoodStampRepository extends JpaRepository<FoodStamp, Long> {

	@Transactional(readOnly = true)
	@Query("select food from FoodStamp food where food.date = :date")
	List<FoodStamp> findByDate(@Param("date") LocalDate date);

}
