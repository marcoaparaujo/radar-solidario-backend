package com.radar.solidario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.radar.solidario.entity.Donate;

@Repository
public interface DonateRepository extends JpaRepository<Donate, Long> {

}
