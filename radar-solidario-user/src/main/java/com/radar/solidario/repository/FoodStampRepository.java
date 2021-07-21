package com.radar.solidario.repository;

import java.util.Date;
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
	@Query(value = "SELECT * FROM food_stamp where date = :date", nativeQuery = true)
	List<FoodStamp> findByDate(@Param("date") Date date);

}
