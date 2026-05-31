package com.alkansya.api.model;

import java.math.BigDecimal;

//import jakarta.persistence.*;
import javax.persistence.*;
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
    private String accountType = "SAVINGS";

    @Column(name = "customer_number", nullable = false)
    private String customerNumber;
    
    @Column(name = "debit_balance", nullable = false)
    private BigDecimal debitBalance;
    
    @Column(name = "credit_limit", nullable = false)
    private Long creditLimit;
    
    @Column(name = "credit_balance", nullable = false)
    private BigDecimal creditBalance;

	@Column(name = "is_active", nullable = false)
    private boolean isActive;
	
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
	
	

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountType=" + accountType + ", customerNumber=" + customerNumber
				+ ", debitBalance=" + debitBalance + ", creditLimit=" + creditLimit + ", creditBalance=" + creditBalance
				+ "]";
	}
    

    
}
