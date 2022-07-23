package tn.esprit.vbank.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.vbank.entities.Signal;
import tn.esprit.vbank.entities.Like;
import tn.esprit.vbank.entities.Post;
import tn.esprit.vbank.entities.User;




@Repository
public interface SignalRepository extends JpaRepository<Signal, Long> {
	
	
	@Modifying
	@Transactional
	@Query(value="delete from Signal where id_Signal= :id", nativeQuery=true)
	void deleteSignalById(@Param("id") Long id);


	@Query(value="select * from t_signal where post_id_post= :idPost and user_id= :idUser", nativeQuery=true)
	Signal getSignalWithIdUserAndIdPost(@Param("idPost") Long idPost,@Param("idUser") Long idUser);

}
