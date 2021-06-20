package com.radar.solidario.service;

import java.util.Optional;

import com.radar.solidario.entity.Funcionario;

public interface FuncionarioService {

	Optional<Funcionario> findById(Long id);

	Funcionario persist(Funcionario Funcionario);

	void deleteById(Long id);

	Optional<Funcionario> findByName(String name);
}
