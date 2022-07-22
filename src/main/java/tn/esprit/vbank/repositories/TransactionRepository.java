package tn.esprit.vbank.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.vbank.entities.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
	
	@Query("select t from Transaction t where compteSourceId=:compteId or compteDestinataireId=:compteId")
	public List<Transaction> getTransactionsByCompteId(@Param("compteId")long compteId);
	
	@Query("select t from Transaction t where (compteSourceId=:compteId or compteDestinataireId=:compteId) "
			+ "and (timestamp >=:debut and timestamp<=:fin)"
			+ "order by timestamp desc")	
	public List<Transaction> getTransactionsParPeriode(@Param("compteId")long compteId, @Param("debut")Timestamp debut, @Param("fin")Timestamp fin);

	@Query("select t from Transaction t where reference=:reference")
	public Transaction getTransactionsByReference(@Param("reference")String reference);
}
