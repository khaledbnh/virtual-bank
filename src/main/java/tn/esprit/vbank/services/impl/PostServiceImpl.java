package tn.esprit.vbank.services.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.vbank.entities.Post;
import tn.esprit.vbank.repositories.PostRepository;
import tn.esprit.vbank.services.IPostService;


@Service
public class PostServiceImpl implements IPostService {

	private static final long serialVersionUID = 6191889143079517027L;

	@Autowired
	private PostRepository postRepository;
	
	@Override
	public List<Post> getPosts() {

		return (List<Post>) postRepository.findAll();
	}

	@Override
	public Post getPostById(Long id) {
		return postRepository.findById(id).get();
	}

	@Override
	public Post addPost(Post a) {
		return postRepository.save(a);
	}

	@Override
	public boolean supprimerPost(Long id) {

		postRepository.deleteById(id);
		return true;
	}

	@Override
	public Post updatePost(Long id, Post a) {

		a.setIdPost(id);
		return postRepository.save(a);
	}

}
