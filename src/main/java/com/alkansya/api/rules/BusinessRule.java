package com.alkansya.api.rules;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alkansya.api.controller.uri.CustomerController;
import com.alkansya.api.model.BankCustomer;

public class BusinessRule {
	private static final Logger logg = LoggerFactory.getLogger(CustomerController.class);

	public boolean isAlphaNumericValid(String customerfield) {
        return customerfield != null
                && !customerfield.isBlank()
                && customerfield.matches("^[a-zA-Z0-9]+$");
    }
	
	public boolean isAlphaValid(String customerfield) {
        return customerfield != null
                && !customerfield.isBlank()
                && customerfield.matches("^[a-zA-Z]+$");
    }
	
	public boolean isNumericValid(String customerfield) {
	    return customerfield != null
	            && !customerfield.isBlank()
	            && customerfield.matches("^\\d+$");
	}
	
	public boolean isBirthDateValid(String customerfield) {
		if (customerfield == null || customerfield.isBlank()) return false;

	    try {
	        LocalDate birthDate = LocalDate.parse(customerfield, DateTimeFormatter.ISO_LOCAL_DATE);
	        LocalDate today = LocalDate.now();
	        if (birthDate.isAfter(today)) {
	        	logg.error("Error! Future birthdate not allowed");
	            return false;
	        }

	        int age = Period.between(birthDate, today).getYears();
	        return age >= 13;

	    } catch (DateTimeParseException e) {
	        return false;
	    }
	}
	
	public boolean isDateValid(String customerfield) {
		if (customerfield == null || customerfield.isBlank()) return false;

	    try {
	        LocalDate entryDate = LocalDate.parse(customerfield, DateTimeFormatter.ISO_LOCAL_DATE);
	        LocalDate today = LocalDate.now();
	        if (entryDate .isAfter(today)) {
	        	logg.error("Error! Future date not allowed");
	            return false;
	        }
	        return true;
	    } catch (DateTimeParseException e) {
	        return false;
	    }
	}
}
