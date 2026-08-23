package com.alkansya.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//import jakarta.persistence.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "bank_accounts")
@Data
public class BankAccount {

//	account_id varchar(45) PK 
//	account_type varchar(45) 
//	customer_number varchar(45) 
//	debit_balance decimal(10,2) 
//	credit_limit bigint 
//	credit_balance decimal(10,2)
    
	@Id
	@Column(name = "account_id", nullable = false)
    private String accountId;

    @Column(name = "account_type", nullable = false)
    private String accountType;

    @Column(name = "customer_number", nullable = false)
    private String customerNumber;
    
    @Column(name = "debit_balance", nullable = false)
    private BigDecimal debitBalance;

    @Column(name = "debit_card_number", nullable = false)
    private String debitCardNumber;
    
    @Column(name = "credit_limit", nullable = false)
    private Long creditLimit;

    @Column(name = "credit_balance", nullable = false)
    private BigDecimal creditBalance;

    @Column(name = "credit_card_number", nullable = false)
    private String creditCardNumber;

    @Column(name = "credit_score", nullable = false)
    private BigDecimal creditScore;

    @Column(name = "credit_points", nullable = false)
    private BigDecimal creditPoints;
    
    @Column(name = "credit_statement_date", nullable = false)
    private LocalDateTime creditStatementDate;

	@Column(name = "is_active", nullable = false)
	@JsonProperty("is_active")
    private String isActive;
	
	@Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;
	
	@Column(name = "date_last_updated", nullable = false)
    private LocalDateTime dateLastUpdated;
	
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public BigDecimal getDebitBalance() {
		return debitBalance;
	}

	public void setDebitBalance(BigDecimal debitBalance) {
		this.debitBalance = debitBalance;
	}

	public String getDebitCardNumber() {
		return debitCardNumber;
	}

	public void setDebitCardNumber(String debitCardNumber) {
		this.debitCardNumber = debitCardNumber;
	}

	public Long getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(Long creditLimit) {
		this.creditLimit = creditLimit;
	}

	public BigDecimal getCreditBalance() {
		return creditBalance;
	}

	public void setCreditBalance(BigDecimal creditBalance) {
		this.creditBalance = creditBalance;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}


	@JsonProperty("is_active")
	public String isActive() {
		return isActive;
	}

	public void setActive(String isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountType=" + accountType + ", customerNumber=" + customerNumber
				+ ", debitBalance=" + debitBalance + ", creditLimit=" + creditLimit + ", creditBalance=" + creditBalance
				+ "]";
	}
    

    
}
