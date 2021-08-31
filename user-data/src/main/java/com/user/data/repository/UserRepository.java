package com.user.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.user.data.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Transactional(readOnly = true)
	List<User> findAllByName(String name);

	@Transactional(readOnly = true)
	Optional<User> findByNis(String nis);
}
