package com.alkansya.api.templ;


import com.alkansya.api.model.BankTransaction;

public interface ITransaction {
	public void validateTransaction(BankTransaction transaction);
	public void processTransaction(BankTransaction transaction);
	public java.util.List<BankTransaction> getTransactionByAccountId(String existingCustId, String sortBy, String ascDesc);
	
}
