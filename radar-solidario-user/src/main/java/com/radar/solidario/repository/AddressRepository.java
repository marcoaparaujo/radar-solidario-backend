package com.radar.solidario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.radar.solidario.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
