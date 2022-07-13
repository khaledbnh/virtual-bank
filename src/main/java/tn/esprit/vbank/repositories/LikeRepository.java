package tn.esprit.vbank.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.vbank.entities.Comment;
import tn.esprit.vbank.entities.Like;
import tn.esprit.vbank.entities.Post;
import tn.esprit.vbank.entities.User;




@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {




}
