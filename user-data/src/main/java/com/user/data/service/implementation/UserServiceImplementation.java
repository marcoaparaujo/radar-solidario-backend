package com.user.data.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.data.dto.user.UserRDTO;
import com.user.data.entity.User;
import com.user.data.repository.UserRepository;
import com.user.data.service.UserService;
import com.user.data.service.processor.UserProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UserProcessor userProcessor;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<UserRDTO> findAllByName(String name) {
		log.info("Start - UserServiceImplementation.findAllByName - Name: {}", name);

		List<User> users = this.userRepository.findAllByName(name);
		List<UserRDTO> userRDTOs = users.stream().map(user -> this.mapper.map(user, UserRDTO.class))
				.collect(Collectors.toList());

		log.info("End - UserServiceImplementation.findAllByName - TokenRDTO: {}", userRDTOs);
		return userRDTOs;
	}

	@Override
	public UserRDTO findByNis(String nis) {
		log.info("Start - UserServiceImplementation.findByNis - NIS: {}", nis);

		User user = this.userProcessor.exists(nis);
		UserRDTO userRDTO = this.mapper.map(user, UserRDTO.class);

		log.info("End - UserServiceImplementation.findByNis - UserRDTO: {}", userRDTO);
		return userRDTO;
	}

	@Override
	public void include(UserRDTO userRDTO) {
		User user = new User();
		user.setNis(userRDTO.getNis());
		user.setName(userRDTO.getName());
		user.setBirth(userRDTO.getBirth().toString());
		
		this.userRepository.save(user);
	}
}
