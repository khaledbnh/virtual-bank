package tn.esprit.vbank.services;

import java.util.List;

import tn.esprit.vbank.entities.Transaction;

public interface ITransactionService {
	
	List<Transaction> getAllTransactions();
	
	Transaction createOrUpdateTransaction(Transaction transaction);
	
	void deleteTransaction(long id);
	
	Transaction getTransaction(long id);
}
