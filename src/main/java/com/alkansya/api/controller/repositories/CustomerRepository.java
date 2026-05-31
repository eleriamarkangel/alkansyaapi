package com.alkansya.api.controller.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkansya.api.model.BankCustomer;

public interface CustomerRepository extends JpaRepository<BankCustomer, String> {

}
