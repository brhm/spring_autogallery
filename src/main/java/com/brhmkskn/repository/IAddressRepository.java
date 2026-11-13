package com.brhmkskn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brhmkskn.model.Address;

public interface IAddressRepository extends JpaRepository<Address, Long>{

}
