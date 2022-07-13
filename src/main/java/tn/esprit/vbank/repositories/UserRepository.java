package tn.esprit.vbank.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.vbank.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
