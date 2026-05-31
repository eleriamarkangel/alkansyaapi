package com.alkansya.api.templ;

import java.util.List;
import java.util.Optional;

import com.alkansya.api.model.BankCustomer;
import com.alkansya.api.model.BankAccount;

public interface ICustomerService {
    public List<BankAccount> getCustomerAccounts(String custId);
    public BankCustomer createCustomer(BankCustomer newAccount);
    public BankCustomer editCustomer(String existingCustId, BankCustomer updatingAccount);
    public BankCustomer deactivateCustomer(long existingCustId);
	public Optional<BankCustomer> getCustomerById(String custId);
}
