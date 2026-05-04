package com.alkansya.api.model;

import java.math.BigDecimal;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bank_customer")
@Data
public class BankTransactions {
//		txn_id varchar(45) PK 
//		txn_date varchar(45) 
//		txn_type varchar(45) 
//		account_id varchar(45) 
//		sent_received varchar(45) 
//		amount decimal(10,2)
	
	@Id
	@Column(name = "txn_id", nullable = false)
    private String txnId;
	
	@Column(name = "txn_date", nullable = false)
    private String txnDate;
	
	@Column(name = "txn_type", nullable = false)
    private String txnType;
	
	@Column(name = "account_id", nullable = false)
    private String accountId;
	
	@Column(name = "sent_received", nullable = false)
    private String sentReceived;
	
	@Column(name = "amount", nullable = false)
    private BigDecimal amount;
	
}