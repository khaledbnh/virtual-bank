package tn.esprit.vbank.services;

import java.sql.Timestamp;
import java.util.List;

import tn.esprit.vbank.entities.Transaction;

public interface ITransactionService {
	
	List<Transaction> getAllTransactions();
	
	Transaction createOrUpdateTransaction(Transaction transaction);
	
	void deleteTransaction(long id);
	
	Transaction getTransaction(long id);
	
	Transaction getTransactionByReference(String reference);
	
	List<Transaction> getTransactionsDuCompte(Long id);
	
	List<Transaction> getTransactionsParPeriode(Long compteId, Timestamp debut, Timestamp fin);
}
