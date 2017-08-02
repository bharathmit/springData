package com.accenture.springdata.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.springdata.entity.Address;


@Repository
public interface AddressRepo extends JpaRepository< Address, Long> {

}
