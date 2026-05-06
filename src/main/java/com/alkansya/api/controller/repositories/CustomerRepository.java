package com.alkansya.api.controller.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkansya.api.model.BankAccount;
import com.alkansya.api.model.BankCustomer;

public interface CustomerRepository extends JpaRepository<BankCustomer, Long> {
	
}
