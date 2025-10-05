package com.alkansya.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "accounts")
@Data
public class Account {
	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
    private Long accountId;

    @Column(name = "acct_type", nullable = false)
    private String accountType = "SAVINGS";

    @Column(name = "acct_first_name", nullable = false)
    private String acctFirstName;
    @Column(name = "acct_last_name", nullable = false)
    private String acctLastName;
    
    @Column(name = "balance", nullable = false)
    private Double balance = 0.0;

    @Column(name = "mobile_num", nullable = false)
    private String mobileNum;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "birthday", nullable = false)
    private String birthday;
    
    @Column(name = "date_added", nullable = false)
    private String dateAdded;

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountType=" + accountType + ", acctFirstName=" + acctFirstName
				+ ", acctLastName=" + acctLastName + ", balance=" + balance + ", mobileNum=" + mobileNum + ", email="
				+ email + ", birthday=" + birthday + ", dateAdded=" + dateAdded + "]";
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAcctFirstName() {
		return acctFirstName;
	}

	public void setAcctFirstName(String acctFirstName) {
		this.acctFirstName = acctFirstName;
	}

	public String getAcctLastName() {
		return acctLastName;
	}

	public void setAcctLastName(String acctLastName) {
		this.acctLastName = acctLastName;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}
    
    

}
