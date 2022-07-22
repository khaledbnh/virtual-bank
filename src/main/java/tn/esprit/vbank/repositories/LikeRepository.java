package tn.esprit.vbank.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.vbank.entities.Like;





@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

	@Modifying
	@Transactional
	@Query(value="delete from t_like where id_like= :id", nativeQuery=true)
	void deleteLikeById(@Param("id") Long id);
	
	@Query(value="select count(*) from t_like where post_id_post= :idPost and tlike= 'LIKE'", nativeQuery=true)
	int getnbrLikeByPost(@Param("idPost") Long idPost);
	
	@Query(value="select count(*) from t_like where post_id_post= :idPost and tlike= 'DISLIKE'", nativeQuery=true)
	int getnbrDisLikeByPost(@Param("idPost") Long idPost);
	
	@Query(value="select * from t_like where post_id_post= :idPost and user_id= :idUser", nativeQuery=true)
	Like getLikeWithIdUserAndIdPost(@Param("idPost") Long idPost,@Param("idUser") Long idUser);


}
