package tn.esprit.vbank.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.vbank.entities.Carte;

@Repository
public interface CarteRepository extends CrudRepository<Carte, Long> {

}
