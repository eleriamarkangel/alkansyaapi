package com.alkansya.api.controller.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.alkansya.api.controller.repositories.BankAccountRepository;
import com.alkansya.api.controller.repositories.BankTransactionRepository;
import com.alkansya.api.model.BankAccount;
import com.alkansya.api.model.BankTransaction;
import com.alkansya.api.templ.ITransaction;

@Service
public class BankTransactionImpl implements ITransaction {

	private static final String ATM_TERMINAL_ID = "TID00143";
	private static final String ATM_TERMINAL_NAME = "ALK-MOA-Bonchon-Manila";
	private static final String ATM_TERMINAL_LOCATION = "Manila";
	private static final String CREDIT_CARD_BILLER_ID = "XXXX";

	@Autowired
	private BankTransactionRepository bankTransactionRepository;

	@Autowired
	private BankAccountRepository bankAccountRepository;

	@Override
	public void validateTransaction(BankTransaction transaction) {
		if(transaction == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction is required");
		}

		if(isBlank(transaction.getTxnId())) {
			transaction.setTxnId(UUID.randomUUID().toString().replace("-", ""));
		}

		if(transaction.getTxnDate() == null) {
			transaction.setTxnDate(LocalDateTime.now());
		}

		if(isBlank(transaction.getTxnStatus())) {
			transaction.setTxnStatus("PENDING");
		}

		if(isBlank(transaction.getTxnType())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction type is required");
		}

		if(isBlank(transaction.getTxnAccountIdFrom())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Source account id is required");
		}

		if(transaction.getTxnAmount() == null || transaction.getTxnAmount().compareTo(BigDecimal.ZERO) <= 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction amount must be greater than 0");
		}

		switch(transaction.getTxnType().toUpperCase()) {
			case "DPST":
			case "WTHDR":
				transaction.setBankTerminalId(ATM_TERMINAL_ID);
				transaction.setBankTerminalName(ATM_TERMINAL_NAME);
				transaction.setBankTerminalLoc(ATM_TERMINAL_LOCATION);
				break;
			case "OLTRANS":
				if(isBlank(transaction.getTxnAccountIdTo())) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Target account id is required for money transfer");
				}
				break;
			case "BILLS":
				if(isBlank(transaction.getBillerId()) || isBlank(transaction.getBillerName())) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Biller id and biller name are required for bills payment");
				}
				break;
			default:
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid transaction type " + transaction.getTxnType());
		}
	}

	@Override
	@Transactional
	public void processTransaction(BankTransaction transaction) {
		validateTransaction(transaction);
		processAccountBalance(transaction);
		transaction.setTxnStatus("SUCCESS");
		bankTransactionRepository.save(transaction);
	}

	@Override
	public List<BankTransaction> getTransactionByAccountId(String existingAcctId, String sortBy, String ascDesc) {
		if(isBlank(existingAcctId)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account id is required");
		}

		Sort sort = Sort.by(getSortDirection(ascDesc), getSortBy(sortBy));
		return bankTransactionRepository.findByTxnAccountIdFromOrTxnAccountIdTo(existingAcctId, existingAcctId, sort);
	}

	private Sort.Direction getSortDirection(String ascDesc) {
		if("DESC".equalsIgnoreCase(ascDesc)) {
			return Sort.Direction.DESC;
		}
		return Sort.Direction.ASC;
	}

	private String getSortBy(String sortBy) {
		if(isBlank(sortBy)) {
			return "txnDate";
		}
		return sortBy;
	}

	private void processAccountBalance(BankTransaction transaction) {
		BankAccount sourceAccount = getActiveAccount(transaction.getTxnAccountIdFrom());
		String txnType = transaction.getTxnType().toUpperCase();

		switch(txnType) {
			case "DPST":
				processDeposit(sourceAccount, transaction.getTxnAmount());
				break;
			case "WTHDR":
				processWithdrawal(sourceAccount, transaction.getTxnAmount());
				break;
			case "OLTRANS":
				processTransfer(sourceAccount, transaction);
				break;
			case "BILLS":
				processBillsPayment(sourceAccount, transaction);
				break;
			default:
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid transaction type " + transaction.getTxnType());
		}
	}

	private void processDeposit(BankAccount account, BigDecimal amount) {
		if(!isDepositAccount(account)) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Only deposit accounts can process deposit transactions");
		}

		account.setDebitBalance(getAmount(account.getDebitBalance()).add(amount));
		bankAccountRepository.save(account);
	}

	private void processWithdrawal(BankAccount account, BigDecimal amount) {
		if(!isDebitAccount(account)) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Only debit accounts can process withdrawal transactions");
		}

		deductDebitBalance(account, amount);
		bankAccountRepository.save(account);
	}

	private void processTransfer(BankAccount sourceAccount, BankTransaction transaction) {
		if(!isDebitAccount(sourceAccount)) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Only debit accounts can process online transfer transactions");
		}

		BankAccount targetAccount = getActiveAccount(transaction.getTxnAccountIdTo());

		if(sourceAccount.getAccountId().equals(targetAccount.getAccountId())) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Source and target account must be different");
		}

		deductDebitBalance(sourceAccount, transaction.getTxnAmount());

		if(!isDebitAccount(targetAccount) && !isDepositAccount(targetAccount)) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Target account must be debit or deposit account");
		}

		targetAccount.setDebitBalance(getAmount(targetAccount.getDebitBalance()).add(transaction.getTxnAmount()));
		bankAccountRepository.save(sourceAccount);
		bankAccountRepository.save(targetAccount);
	}

	private void processBillsPayment(BankAccount account, BankTransaction transaction) {
		if(isCreditAccount(account)) {
			if(isCreditCardBiller(transaction)) {
				throw new ResponseStatusException(HttpStatus.CONFLICT, "Cannot pay credit card biller using credit balance");
			}
			addCreditBalance(account, transaction.getTxnAmount());
		} else if(isDebitAccount(account)) {
			deductDebitBalance(account, transaction.getTxnAmount());
		} else {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Only debit or credit accounts can process bills payment transactions");
		}

		bankAccountRepository.save(account);
	}

	private BankAccount getActiveAccount(String accountId) {
		BankAccount account = bankAccountRepository.findById(accountId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bank account id " + accountId + " does not exist"));

		if(!"Y".equals(account.isActive())) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Bank account id " + accountId + " is not active");
		}

		return account;
	}

	private void deductDebitBalance(BankAccount account, BigDecimal amount) {
		BigDecimal currentBalance = getAmount(account.getDebitBalance());
		if(currentBalance.compareTo(amount) < 0) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Insufficient debit balance");
		}
		account.setDebitBalance(currentBalance.subtract(amount));
	}

	private void addCreditBalance(BankAccount account, BigDecimal amount) {
		BigDecimal currentBalance = getAmount(account.getCreditBalance());
		BigDecimal newBalance = currentBalance.add(amount);
		BigDecimal creditLimit = BigDecimal.valueOf(account.getCreditLimit());

		if(newBalance.compareTo(creditLimit) > 0) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Insufficient credit limit");
		}

		account.setCreditBalance(newBalance);
	}

	private boolean isCreditCardBiller(BankTransaction transaction) {
		return equalsIgnoreCase(CREDIT_CARD_BILLER_ID, transaction.getBillerId())
				|| containsIgnoreCase(transaction.getBillerName(), "CREDIT CARD")
				|| containsIgnoreCase(transaction.getBillerName(), "CREDIT CARDS");
	}

	private boolean isCreditAccount(BankAccount account) {
		return "CREDIT".equalsIgnoreCase(account.getAccountType());
	}

	private boolean isDebitAccount(BankAccount account) {
		return "DEBIT".equalsIgnoreCase(account.getAccountType());
	}

	private boolean isDepositAccount(BankAccount account) {
		return "DEPOSIT".equalsIgnoreCase(account.getAccountType());
	}

	private BigDecimal getAmount(BigDecimal amount) {
		if(amount == null) {
			return BigDecimal.ZERO;
		}
		return amount;
	}

	private boolean equalsIgnoreCase(String firstValue, String secondValue) {
		return !isBlank(firstValue) && !isBlank(secondValue) && firstValue.equalsIgnoreCase(secondValue);
	}

	private boolean containsIgnoreCase(String value, String searchValue) {
		return !isBlank(value) && value.toUpperCase().contains(searchValue);
	}

	private boolean isBlank(String value) {
		return value == null || value.isBlank();
	}
}
