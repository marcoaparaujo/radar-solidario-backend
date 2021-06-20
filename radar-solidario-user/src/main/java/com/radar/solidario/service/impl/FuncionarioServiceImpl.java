package com.radar.solidario.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.radar.solidario.entity.Funcionario;
import com.radar.solidario.repository.FuncionarioRepository;
import com.radar.solidario.service.FuncionarioService;

public class FuncionarioServiceImpl implements FuncionarioService {

	private static final Logger log = LoggerFactory.getLogger(FuncionarioServiceImpl.class);

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Override
	public Optional<Funcionario> findById(Long id) {
		log.info("Buscando um cliente pelo Id: {}", id);
		return this.funcionarioRepository.findById(id);
	}

	@Override
	public Optional<Funcionario> findByName(String name) {
		log.info("Buscando um cliente pelo nome: {}", name);
		return this.funcionarioRepository.findByName(name);
	}

	@Override
	public Funcionario persist(Funcionario Funcionario) {
		log.info("Persistindo cliente: {}", Funcionario);
		return this.funcionarioRepository.save(Funcionario);
	}

	@Override
	public void deleteById(Long id) {
		log.info("Removendo um cliente pelo Id: {}", id);
		this.funcionarioRepository.deleteById(id);
	}
}
