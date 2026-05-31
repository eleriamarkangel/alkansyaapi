package com.alkansya.api.templ;

import java.util.Optional;

import com.alkansya.api.model.BankAccount;

public interface IBankAccount {
    public Optional<BankAccount> getAccountById(String accountId);
    public BankAccount createAccount(BankAccount newAccount);
    public void deactivateAccount(String existingAccount);
}
