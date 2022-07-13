package tn.esprit.vbank.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.vbank.entities.Transaction;
import tn.esprit.vbank.services.ITransactionService;

@RestController
public class TransactionRestController {
	
	@Autowired 
	ITransactionService transactionService; 
 
	@GetMapping("/getAllTransactions")
	public List<Transaction> getAllTransactions() {
		List<Transaction> result = transactionService.getAllTransactions();
		return result;
	}

}
