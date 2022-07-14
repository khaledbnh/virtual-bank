package tn.esprit.vbank.repositories;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.vbank.entities.Compte;

public interface CompteRepository extends CrudRepository<Compte, Long> {

}
