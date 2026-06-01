package com.alkansya.api.controller.service;

import java.math.BigDecimal;
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
		newAccount.setActive("Y");
		return bankAccountRepository.save(newAccount);
	}

	@Override
	public BankAccount deactivateAccount(String existingAccount) {
		BankAccount account = bankAccountRepository.findById(existingAccount)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bank account id " + existingAccount + " does not exist"));

		if(!"Y".equals(account.isActive())) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Bank account id " + existingAccount + " is already inactive");
		}

		if("CREDIT".equalsIgnoreCase(account.getAccountType())
				&& account.getCreditBalance() != null
				&& account.getCreditBalance().compareTo(BigDecimal.ZERO) != 0) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Credit account balance must be 0 before deactivating account");
		}

		account.setActive("N");
		return bankAccountRepository.save(account);
	}
}
