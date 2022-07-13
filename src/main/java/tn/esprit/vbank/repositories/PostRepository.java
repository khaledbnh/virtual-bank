package tn.esprit.vbank.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.vbank.entities.Post;




@Repository
public interface PostRepository extends JpaRepository<Post, Long> {




}
