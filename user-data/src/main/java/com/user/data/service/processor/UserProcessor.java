package com.user.data.service.processor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.user.data.entity.User;
import com.user.data.exception.user.notFound.UserNotFoundException;
import com.user.data.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserProcessor {

	@Autowired
	private UserRepository userRepository;

	public User exists(String nis) {
		log.info("Start - UserProcessor.exists - NIS: {}", nis);

		Optional<User> optUser = this.userRepository.findByNis(nis);
		if (optUser.isEmpty()) {
			log.error("UserNotFoundException - NIS: {}", nis);
			throw new UserNotFoundException();
		}

		log.info("End - UserProcessor.exists - User: {}", optUser.get());
		return optUser.get();
	}
}
