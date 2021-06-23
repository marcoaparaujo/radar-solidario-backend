package com.radar.solidario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.radar.solidario.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
