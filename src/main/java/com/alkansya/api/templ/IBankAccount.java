package com.alkansya.api.templ;

import java.util.List;
import java.util.Optional;

import com.alkansya.api.model.BankAccount;
import com.alkansya.api.model.BankCustomer;

public interface IBankAccount {
    public Optional<BankAccount> getAccount(long acctId, long existingCustId);
    public BankAccount createAccount(BankCustomer existingCustId);
    public void deactivateAccount(long existingAccount);
}
