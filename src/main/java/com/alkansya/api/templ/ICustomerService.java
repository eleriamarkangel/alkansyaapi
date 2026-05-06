package com.alkansya.api.templ;

import java.util.List;
import java.util.Optional;

import com.alkansya.api.model.BankCustomer;
import com.alkansya.api.model.BankAccount;

public interface ICustomerService {
    public Optional<BankCustomer> getCustomer(long custId);
    public List<BankAccount> getCustomerAccounts(long custId);
    public BankCustomer createCustomer(BankCustomer newAccount);
    public BankCustomer editCustomer(long existingCustId, BankCustomer updatingAccount);
    public BankCustomer deactivateCustomer(long existingCustId);
}
