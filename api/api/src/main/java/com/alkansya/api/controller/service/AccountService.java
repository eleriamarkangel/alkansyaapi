package com.alkansya.api.controller.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alkansya.api.controller.repositories.AccountRepository;
import com.alkansya.api.model.Account;

@Service
public class AccountService {
	
	@Autowired
    private AccountRepository accountRepository;

    public Optional<Account> getAccountById(Long accountId) {
        return accountRepository.findById(accountId);
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);  // Saves the account to the database
    }

    public void deleteAccount(Long accountNumber) {
        accountRepository.deleteById(accountNumber);  // Deletes the account by ID
    }
    
    public ResponseEntity<String> returnResponseByRequestType(String requestType, int responseType) {
        if((requestType == "createAccount") && (responseType == 1)){
            return new ResponseEntity<>("Add order success!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Add order failed!", HttpStatus.BAD_REQUEST);
        }

    }
}
