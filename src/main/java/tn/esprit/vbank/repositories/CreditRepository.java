package tn.esprit.vbank.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.vbank.entities.Credit;

@Repository
public interface CreditRepository extends CrudRepository<Credit, Long>{

}
