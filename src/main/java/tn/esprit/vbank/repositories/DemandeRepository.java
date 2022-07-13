package tn.esprit.vbank.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.vbank.entities.Demande;
@Repository
public interface DemandeRepository extends  CrudRepository<Demande, Long>  {

}
