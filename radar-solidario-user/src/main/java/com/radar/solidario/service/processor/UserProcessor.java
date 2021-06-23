package com.radar.solidario.service.processor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.radar.solidario.entity.User;
import com.radar.solidario.exception.user.notFound.UserNotFoundException;
import com.radar.solidario.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserProcessor {

	@Autowired
	private UserRepository personRepository;

	public User exists(Long id) {
		log.info("Start - UserProcessor.exists - Id: {}", id);

		Optional<User> optUser = this.personRepository.findById(id);
		if (optUser.isEmpty()) {
			log.error("UserNotFoundException - Id: {}", id);
			throw new UserNotFoundException();
		}

		log.info("End - UserProcessor.exists - User: {}", optUser.get());
		return optUser.get();
	}
}
