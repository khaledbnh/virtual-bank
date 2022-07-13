package tn.esprit.vbank.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.vbank.entities.Employe;

@Repository
public interface EmployeRepository  extends CrudRepository<Employe, Long> {

}
