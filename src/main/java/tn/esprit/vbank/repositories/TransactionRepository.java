package tn.esprit.vbank.repositories;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.vbank.entities.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}
