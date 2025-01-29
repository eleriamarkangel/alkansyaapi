package com.alkansya.api.controller.uri;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alkansya.api.controller.repositories.AccountRepository;
import com.alkansya.api.controller.service.AccountService;
import com.alkansya.api.model.Account;

@RestController
@RequestMapping("/accounts")
public class Controller {
	private static final Logger logg = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private AccountService accountService;

    @GetMapping("/get/{accountId}")
    public Optional<Account> getAccount(@PathVariable Long accountId) {
		return accountService.getAccountById(accountId);
    }

    @PostMapping("/add")
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @DeleteMapping("/delete/{accountNum}")
    public void deleteAccount(@PathVariable Long accountNum) {
        accountService.deleteAccount(accountNum);
    }
    
    
}