package tn.esprit.vbank.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.vbank.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	@Query(value = "SELECT post_id_post FROM banque.comment"
			+ " where post_id_post "
			+ "in (select id_post from post where DATEDIFF(CURDATE(),date_creation) < 7) "
			+ " group by post_id_post  "
			+ "ORDER BY COUNT(post_id_post) DESC LIMIT 1;", nativeQuery = true)
	int getIdPostWithMaxComments();

	@Query(value = "SELECT post_id_post FROM banque.t_like where post_id_post in (select id_post from post where DATEDIFF(CURDATE(),date_creation) < 7) and tlike= 'LIKE' group by post_id_post  ORDER BY COUNT(post_id_post) DESC LIMIT 1;"
, nativeQuery = true)
	int getIdPostWithMaxLike();
	
	@Query(value = "select * from post where status like 'ACTIVE'", nativeQuery = true)
	List<Post> getListPostWithStatusActive();
	
	@Query(value = "select * from post where status like 'DESACTIVE'", nativeQuery = true)
	List<Post> getListPostWithStatusDesActive();
}


