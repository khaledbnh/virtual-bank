package tn.esprit.vbank.services.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.vbank.entities.Transaction;
import tn.esprit.vbank.repositories.TransactionRepository;
import tn.esprit.vbank.services.ITransactionService;

@Service
public class TransactionServiceImpl implements ITransactionService {
    
	@Autowired
    TransactionRepository transactionRepo;

	@Override
	public List<Transaction> getAllTransactions() {
		return (List<Transaction>) transactionRepo.findAll();
	}

	@Override
	public Transaction createOrUpdateTransaction(Transaction transaction) {
		return transactionRepo.save(transaction);
	}

	@Override
	public void deleteTransaction(long id) {
		transactionRepo.deleteById(id);
	}

	@Override
	public Transaction getTransaction(long id) {
		return transactionRepo.findById(id).get();
	}
	
	@Override
	public Transaction getTransactionByReference(String reference) {
		return transactionRepo.getTransactionsByReference(reference);
	}

	@Override
	public List<Transaction> getTransactionsDuCompte(Long id) {
		return transactionRepo.getTransactionsByCompteId(id);
	}
	
	@Override
	public List<Transaction> getTransactionsParPeriode(Long compteId, Timestamp debut, Timestamp fin) {
		return transactionRepo.getTransactionsParPeriode(compteId, debut, fin);
	}

}
