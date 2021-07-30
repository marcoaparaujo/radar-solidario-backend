package com.radar.solidario.service.processor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.radar.solidario.entity.Address;
import com.radar.solidario.exception.address.AddressNotFoundException;
import com.radar.solidario.repository.AddressRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AddressProcessor {

	@Autowired
	private AddressRepository addressRepository;

	public Address exists(Long id) {
		log.info("Start - AddressProcessor.exists - Id: {}", id);

		Optional<Address> optAddress = this.addressRepository.findById(id);
		if (optAddress.isEmpty()) {
			log.error("AddressNotFoundException - Id: {}", id);
			throw new AddressNotFoundException();
		}

		log.info("End - AddressProcessor.exists - Address: {}", optAddress.get());
		return optAddress.get();
	}

	public Address merge(Address address) {
		log.info("Start - AddressProcessor.merge - Address: {}", address);

		Address original = this.exists(address.getId());
		original = this.combine(address, original);

		log.info("End - AddressProcessor.merge - Address: {}", original);
		return original;
	}

	public Address combine(Address source, Address destination) {
		log.info("Start - AddressProcessor.combine - Address: {}, Address: {}", source, destination);

		destination.setCep(source.getCep());
		destination.setPhone(source.getPhone());
		destination.setNumber(source.getNumber());
		destination.setStreet(source.getStreet());
		destination.setNeighborhood(source.getNeighborhood());

		log.info("End - AddressProcessor.combine - Address: {}", destination);
		return destination;
	}
}
