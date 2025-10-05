package com.alkansya.api.controller.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkansya.api.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
}
