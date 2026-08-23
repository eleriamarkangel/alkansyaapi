package com.alkansya.api.controller.uri;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkansya.api.controller.service.BankTransactionImpl;
import com.alkansya.api.model.BankTransaction;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	private BankTransactionImpl bankTransactionService;

	@PostMapping("/processTransaction")
	public void processTransaction(@RequestBody BankTransaction transaction) {
		bankTransactionService.processTransaction(transaction);
	}

	@GetMapping({
			"/getTransactionByAcctId/{existingAcctId}/sort-{sortBy}-ascdesc",
			"/getTransactionByAcctId/{existingAcctId}/sort-{sortBy}-ascdesc-{ascDesc}"
	})
	public List<BankTransaction> getTransactionByAccountId(
			@PathVariable String existingAcctId,
			@PathVariable String sortBy,
			@PathVariable(required = false) String ascDesc) {
		return bankTransactionService.getTransactionByAccountId(existingAcctId, sortBy, ascDesc);
	}
}
