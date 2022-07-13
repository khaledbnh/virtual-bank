package tn.esprit.vbank.services;

import java.util.List;

import tn.esprit.vbank.entities.Post;


public interface IPostService {

	public List<Post> getPosts();

	public Post getPostById(Long id);

	public Post addPost(Post a);

	public boolean supprimerPost(Long id);

	public Post updatePost(Long id, Post a);
}
