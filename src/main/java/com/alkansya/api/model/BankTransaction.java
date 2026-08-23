package com.alkansya.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bank_transactions")
@Data
public class BankTransaction {
	
	@Id
	@Column(name = "txn_id", nullable = false)
    private String txnId;
	
	@Column(name = "txn_date", nullable = false)
    private LocalDateTime txnDate;
	
	@Column(name = "txn_type", nullable = false)
    private String txnType;
	
	@Column(name = "txn_account_id_from", nullable = false)
    private String txnAccountIdFrom;	
	
	@Column(name = "txn_account_id_to")
    private String txnAccountIdTo;
	
	@Column(name = "txn_amount", nullable = false)
    private BigDecimal txnAmount;
	
	@Column(name = "txn_status", nullable = false)
    private String txnStatus;
	
	@Column(name = "biller_id")
    private String billerId;

	@Column(name = "biller_name")
    private String billerName;

	@Column(name = "notes")
    private String notes;
	
	@Column(name = "bank_terminal_id")
    private String bankTerminalId;
	
	@Column(name = "bank_terminal_name")
    private String bankTerminalName;
	
	@Column(name = "bank_terminal_loc")
    private String bankTerminalLoc;

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public LocalDateTime getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(LocalDateTime txnDate) {
		this.txnDate = txnDate;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public String getTxnAccountIdFrom() {
		return txnAccountIdFrom;
	}

	public void setTxnAccountIdFrom(String txnAccountIdFrom) {
		this.txnAccountIdFrom = txnAccountIdFrom;
	}

	public String getTxnAccountIdTo() {
		return txnAccountIdTo;
	}

	public void setTxnAccountIdTo(String txnAccountIdTo) {
		this.txnAccountIdTo = txnAccountIdTo;
	}

	public BigDecimal getTxnAmount() {
		return txnAmount;
	}

	public void setTxnAmount(BigDecimal txnAmount) {
		this.txnAmount = txnAmount;
	}

	public String getTxnStatus() {
		return txnStatus;
	}

	public void setTxnStatus(String txnStatus) {
		this.txnStatus = txnStatus;
	}

	public String getBillerId() {
		return billerId;
	}

	public void setBillerId(String billerId) {
		this.billerId = billerId;
	}

	public String getBillerName() {
		return billerName;
	}

	public void setBillerName(String billerName) {
		this.billerName = billerName;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getBankTerminalId() {
		return bankTerminalId;
	}

	public void setBankTerminalId(String bankTerminalId) {
		this.bankTerminalId = bankTerminalId;
	}

	public String getBankTerminalName() {
		return bankTerminalName;
	}

	public void setBankTerminalName(String bankTerminalName) {
		this.bankTerminalName = bankTerminalName;
	}

	public String getBankTerminalLoc() {
		return bankTerminalLoc;
	}

	public void setBankTerminalLoc(String bankTerminalLoc) {
		this.bankTerminalLoc = bankTerminalLoc;
	}
}
