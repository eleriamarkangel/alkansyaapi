package com.alkansya.api.controller.uri;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alkansya.api.controller.service.CustomerServiceImpl;
import com.alkansya.api.model.BankAccount;
import com.alkansya.api.model.BankCustomer;

@RestController
@RequestMapping("/customers") //base URL http://localhost:8008/customers/register
public class CustomerController {
	private static final Logger logg = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerServiceImpl custService;
    
    @GetMapping("/index")
    public String getIndex() {
        return "Index";
    }

    @PostMapping("/register")
    public BankCustomer createCustomer(@RequestBody BankCustomer newAccount) {
    	return custService.createCustomer(newAccount);
	}
    
//    @GetMapping("/get/{accountId}")
//    public Optional<BankAccount> getAccount(@PathVariable Long accountId) {
//		return custService.getAccountById(accountId);
//    }
//
//    @GetMapping("/get/accounts")
//    public List<BankAccount> getAccounts() {
//		return custService.getAccounts();
//    }
//
//    @PostMapping("/add")
//    public BankAccount createAccount(@RequestBody BankAccount account) {
//        return custService.createAccount(account);
//    }
//
//    @DeleteMapping("/delete/{accountNum}")
//    public void deleteAccount(@PathVariable Long accountNum) {
//    	custService.deleteAccount(accountNum);
//    }
    
    
}