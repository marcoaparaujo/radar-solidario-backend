package com.radar.solidario.service;

public interface SecurityService {

	void lock(Long id);

	void unlock(Long id);

	void enable(Long id);

	void unable(Long id);
}
