package tn.esprit.vbank.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.vbank.entities.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
	
	@Query("select t from Transaction t where compteSourceId=:compteId or compteDestinataireId=:compteId")
	public List<Transaction> getTransactionsByCompteId(@Param("compteId")long compteId);

}
