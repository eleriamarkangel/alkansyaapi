package com.alkansya.api.controller.uri;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkansya.api.controller.service.BankAccountServiceImpl;
import com.alkansya.api.model.BankAccount;

@RestController
@RequestMapping("/accounts")
public class BankAccountController {

	@Autowired
	private BankAccountServiceImpl bankAccountService;

	@PostMapping("/register")
	public BankAccount createAccount(@RequestBody BankAccount newAccount) {
		return bankAccountService.createAccount(newAccount);
	}

	@GetMapping("/getAccount/{accountId}")
	public Optional<BankAccount> getAccountById(@PathVariable String accountId) {
		return bankAccountService.getAccountById(accountId);
	}
}
