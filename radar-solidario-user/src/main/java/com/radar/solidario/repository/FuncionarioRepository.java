package com.radar.solidario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.radar.solidario.entity.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	@Transactional(readOnly = true)
	Optional<Funcionario> findById(Long id);
	
	Optional<Funcionario> findByName(String name);
}
