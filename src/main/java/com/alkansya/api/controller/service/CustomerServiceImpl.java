package com.alkansya.api.controller.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.alkansya.api.controller.repositories.BankAccountRepository;
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

	@Autowired
	private BankAccountRepository bankAccountRepository;
	

	@Override
	public Optional<BankCustomer> getCustomerById(String custId) {
		Optional<BankCustomer> customer = customerRepository.findById(custId);
		if(customer.isPresent()) {
			return customer;
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer id " + custId + " does not exist");
	}

	@Override
	public List<BankAccount> getCustomerAccounts(String custId) {
		BankCustomer customer = customerRepository.findById(custId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer id " + custId + " does not exist"));

		if(!"Y".equals(customer.isActive())) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Customer id " + custId + " is not active");
		}

		return bankAccountRepository.findByCustomerNumberAndIsActive(custId, "Y");
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
			return newCustomer;
		}
	}

	@Override
	public BankCustomer editCustomer(String existingCustId, BankCustomer updatingAccount) {
		BusinessRule businessRule = new BusinessRule();
		BankCustomer existingCustomer = customerRepository.findById(existingCustId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer id " + existingCustId + " does not exist"));

		if(!isCustomerUpdateValid(updatingAccount, businessRule)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid or empty customer field/s");
		}

		boolean hasChanges = !Objects.equals(existingCustomer.getcFirstName(), updatingAccount.getcFirstName())
				|| !Objects.equals(existingCustomer.getcLastName(), updatingAccount.getcLastName())
				|| !Objects.equals(existingCustomer.getMobileNbr(), updatingAccount.getMobileNbr())
				|| !Objects.equals(existingCustomer.getBirthday(), updatingAccount.getBirthday())
				|| !Objects.equals(existingCustomer.getModifiedBy(), updatingAccount.getModifiedBy());

		if(!hasChanges) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "No changes detected. Information will not be saved.");
		}

		existingCustomer.setcFirstName(updatingAccount.getcFirstName());
		existingCustomer.setcLastName(updatingAccount.getcLastName());
		existingCustomer.setMobileNbr(updatingAccount.getMobileNbr());
		existingCustomer.setBirthday(updatingAccount.getBirthday());
		existingCustomer.setModifiedBy(updatingAccount.getModifiedBy());
		existingCustomer.setDateLastUpdated(LocalDateTime.now());

		return customerRepository.save(existingCustomer);
	}

	@Override
	public BankCustomer deactivateCustomer(String existingCustId) {
		BankCustomer existingCustomer = customerRepository.findById(existingCustId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer id " + existingCustId + " does not exist"));

		if(!"Y".equals(existingCustomer.isActive())) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Customer id " + existingCustId + " is already inactive");
		}

		List<BankAccount> activeAccounts = bankAccountRepository.findByCustomerNumberAndIsActive(existingCustId, "Y");
		boolean hasUnsettledCredit = activeAccounts.stream()
				.anyMatch(account -> "CREDIT".equalsIgnoreCase(account.getAccountType())
						&& account.getCreditBalance() != null
						&& account.getCreditBalance().compareTo(BigDecimal.ZERO) != 0);

		if(hasUnsettledCredit) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Credit account balance must be 0 before deactivating customer");
		}

		activeAccounts.forEach(account -> account.setActive("N"));
		bankAccountRepository.saveAll(activeAccounts);

		existingCustomer.setActive("N");
		existingCustomer.setDateLastUpdated(LocalDateTime.now());

		return customerRepository.save(existingCustomer);
	}

	private boolean isCustomerUpdateValid(BankCustomer customer, BusinessRule businessRule) {
		return customer != null
				&& businessRule.isAlphaValid(customer.getcFirstName())
				&& businessRule.isAlphaValid(customer.getcLastName())
				&& businessRule.isNumericValid(customer.getMobileNbr())
				&& businessRule.isBirthDateValid(customer.getBirthday())
				&& businessRule.isAlphaValid(customer.getModifiedBy());
	}

}
