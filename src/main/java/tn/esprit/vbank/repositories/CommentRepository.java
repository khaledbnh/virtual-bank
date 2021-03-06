package tn.esprit.vbank.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.vbank.entities.Comment;
import tn.esprit.vbank.entities.Post;
import tn.esprit.vbank.entities.User;




@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	
	@Modifying
	@Transactional
	@Query(value="delete from comment where id_comment= :id", nativeQuery=true)
	void deleteCommentById(@Param("id") Long id);




}
