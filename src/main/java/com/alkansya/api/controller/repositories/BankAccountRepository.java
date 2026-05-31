package com.alkansya.api.controller.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkansya.api.model.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
	List<BankAccount> findByCustomerNumberAndIsActive(String customerNumber, boolean isActive);

}
