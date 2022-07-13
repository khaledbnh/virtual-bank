package tn.esprit.vbank.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.vbank.entities.Comment;
import tn.esprit.vbank.entities.Post;
import tn.esprit.vbank.entities.User;




@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {




}
