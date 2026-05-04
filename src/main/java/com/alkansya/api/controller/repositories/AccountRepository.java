package com.alkansya.api.controller.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkansya.api.model.BankAccount;

public interface AccountRepository extends JpaRepository<BankAccount, Long> {
	
}
