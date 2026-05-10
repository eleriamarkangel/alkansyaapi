package com.alkansya.api.controller.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alkansya.api.controller.repositories.CustomerRepository;
import com.alkansya.api.controller.uri.CustomerController;
import com.alkansya.api.model.BankAccount;
import com.alkansya.api.model.BankCustomer;
import com.alkansya.api.rules.BusinessRule;
import com.alkansya.api.templ.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService{
	private static final Logger logg = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
    private CustomerRepository customerRepository;
	

	@Override
	public Optional<BankCustomer> getCustomer(long custId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<BankAccount> getCustomerAccounts(long custId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankCustomer createCustomer(BankCustomer newCustomer){
		BusinessRule businessRule = new BusinessRule();
		BankCustomer customer = new BankCustomer();
		if(businessRule.isAlphaValid(newCustomer.getcFirstName()) &&
			businessRule.isAlphaValid(newCustomer.getcLastName()) &&
			businessRule.isNumericValid(newCustomer.getMobileNbr()) &&
			businessRule.isBirthDateValid(newCustomer.getBirthday()) &&
			businessRule.isAlphaValid(newCustomer.getModifiedBy())
				) {
		    customer.setCustomerNumber(UUID.randomUUID().toString().replace("-", ""));
		    customer.setDateCreated(LocalDateTime.now());
		    customer.setDateLastUpdated(LocalDateTime.now());
		    customer.setActive("Y");
		    //mandated fields
		    customer.setcFirstName(newCustomer.getcFirstName());
		    customer.setcLastName(newCustomer.getcLastName());
		    customer.setMobileNbr(newCustomer.getMobileNbr());
		    customer.setBirthday(newCustomer.getBirthday());
		    customer.setModifiedBy(newCustomer.getModifiedBy());
		    //mandated fields
		    logg.info("Customer created with id " + customer.getCustomerNumber());
			return customerRepository.save(customer);
		} else {
		    logg.error("Invalid field/s. Please double check.");
		    if (!businessRule.isAlphaValid(newCustomer.getcFirstName())) {
		    	logg.error("First name must contain letters only");
		    }
		    if (!businessRule.isAlphaValid(newCustomer.getcLastName())) {
		    	logg.error("Last name must contain letters only");
		    }
		    if (!businessRule.isNumericValid(newCustomer.getMobileNbr())) {
		    	logg.error("Mobile number must contain digits only");
		    }
		    if (!businessRule.isBirthDateValid(newCustomer.getBirthday())) {
		    	logg.error("Invalid birthday or underage (min 13 years old)");
		    }
		    if (!businessRule.isAlphaValid(newCustomer.getModifiedBy())) {
		    	logg.error("ModifiedBy must contain letters only");
		    }
			return null;
		}
	}

	@Override
	public BankCustomer editCustomer(long existingCustId, BankCustomer updatingAccount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankCustomer deactivateCustomer(long existingCustId) {
		// TODO Auto-generated method stub
		return null;
	}
//    public Optional<BankAccount> getAccountById(Long accountId) {
//    	logg.info("account id " + accountId);
//        return accountRepository.findById(accountId);
//    }
//    
//    public List<BankAccount> getAccounts() {
//    	logg.info("ALL accounts...");
//        return accountRepository.findAll();
//    }
//
//    public BankAccount createAccount(BankAccount account) {
//    	logg.info("Creating account.."); 
//    	if(isRequestValid(account)){
//    		return accountRepository.save(account);
//    	} else {
//    		return null; // response
//    	}
//      return accountRepository.save(account);  // Saves the account to the database
// Saves the account to the database
//    }
//
//    public void deleteAccount(Long accountNumber) {
//        accountRepository.deleteById(accountNumber);  // Deletes the account by ID
//    }
//    
//    public ResponseEntity<String> returnResponseByRequestType(String requestType, int responseType) {
//        if((requestType == "createAccount") && (responseType == 1)){
//            return new ResponseEntity<>("Add order success!", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Add order failed!", HttpStatus.BAD_REQUEST);
//        }
//
//    }
//    
//    public boolean isRequestValid(BankAccount acct) {
//    	return false;
//    }

}
