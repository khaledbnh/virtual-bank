package tn.esprit.vbank.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.vbank.entities.Abonnement;

@Repository
public interface AbonnementRepository extends CrudRepository<Abonnement, Long> {

}
