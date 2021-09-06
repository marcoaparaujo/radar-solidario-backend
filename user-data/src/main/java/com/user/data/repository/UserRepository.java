package com.user.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.data.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findAllByName(String name);

	Optional<User> findByNis(String nis);
}
