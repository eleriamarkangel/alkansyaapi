package com.alkansya.api.controller.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.alkansya.api.controller.repositories.BankAccountRepository;
import com.alkansya.api.controller.repositories.CustomerRepository;
import com.alkansya.api.model.BankAccount;
import com.alkansya.api.templ.IBankAccount;

@Service
public class BankAccountServiceImpl implements IBankAccount {

	@Autowired
	private BankAccountRepository bankAccountRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Optional<BankAccount> getAccountById(String accountId) {
		Optional<BankAccount> account = bankAccountRepository.findById(accountId);
		if(account.isPresent()) {
			return account;
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bank account id " + accountId + " does not exist");
	}

	@Override
	public BankAccount createAccount(BankAccount newAccount) {
		String customerNumber = newAccount.getCustomerNumber();

		if(customerNumber == null || customerNumber.isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer number is required");
		}

		if(!customerRepository.existsById(customerNumber)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer id " + customerNumber + " does not exist");
		}

		newAccount.setAccountId(UUID.randomUUID().toString().replace("-", ""));
		newAccount.setActive(true);
		return bankAccountRepository.save(newAccount);
	}

	@Override
	public void deactivateAccount(String existingAccount) {
		// TODO Auto-generated method stub
	}
}
