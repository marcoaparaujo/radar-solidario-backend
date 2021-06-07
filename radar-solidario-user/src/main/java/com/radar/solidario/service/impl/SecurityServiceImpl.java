package com.radar.solidario.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radar.solidario.entity.Authentication;
import com.radar.solidario.repository.AuthenticationRepository;
import com.radar.solidario.service.SecurityService;
import com.radar.solidario.service.prcr.AuthenticationProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	private AuthenticationProcessor authenticationProcessor;

	@Autowired
	private AuthenticationRepository authenticationRepository;

	@Override
	public void lock(Long id) {
		log.info("Start - SecurityServiceImpl.lock - Id: {}", id);

		Authentication authentication = this.authenticationProcessor.exists(id);
		authentication.setIsLocked(true);

		this.authenticationRepository.save(authentication);

		log.info("End - SecurityServiceImpl.lock");
	}

	@Override
	public void unlock(Long id) {
		log.info("Start - SecurityServiceImpl.unlock - Id: {}", id);

		Authentication authentication = this.authenticationProcessor.exists(id);
		authentication.setIsLocked(false);

		this.authenticationRepository.save(authentication);

		log.info("End - SecurityServiceImpl.unlock");
	}

	@Override
	public void enable(Long id) {
		log.info("Start - SecurityServiceImpl.enable - Id: {}", id);

		Authentication authentication = this.authenticationProcessor.exists(id);
		authentication.setIsEnabled(true);

		this.authenticationRepository.save(authentication);

		log.info("End - SecurityServiceImpl.enable");
	}

	@Override
	public void unable(Long id) {
		log.info("Start - SecurityServiceImpl.unable - Id: {}", id);

		Authentication authentication = this.authenticationProcessor.exists(id);
		authentication.setIsEnabled(false);

		this.authenticationRepository.save(authentication);

		log.info("End - SecurityServiceImpl.unable");
	}
}
