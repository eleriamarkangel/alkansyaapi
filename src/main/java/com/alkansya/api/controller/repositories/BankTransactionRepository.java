package com.alkansya.api.controller.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.alkansya.api.model.BankTransaction;

public interface BankTransactionRepository extends JpaRepository<BankTransaction, String> {
	List<BankTransaction> findByTxnAccountIdFromOrTxnAccountIdTo(String txnAccountIdFrom, String txnAccountIdTo, Sort sort);
}
