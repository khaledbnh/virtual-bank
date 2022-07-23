package tn.esprit.vbank.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.vbank.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT * FROM USER WHERE USERNAME = ?1", nativeQuery = true)
    User findByUsername(String username);

    @Query(value = "SELECT * FROM USER WHERE EMAIL = ?1", nativeQuery = true)
    User findByEmail(String email);

    User findByToken(String token);
}
