package tn.esprit.vbank.services;

import java.util.List;

import tn.esprit.vbank.entities.Like;


public interface ILikeService {

	public List<Like> getLikes();

	public Like getLikeById(Long id);

	public Like addLike(Like a);

	public boolean supprimerLike(Long id);

	public Like updateLike(Long id, Like a);
	
	public int getNbrLikeByPost(Long id);
	
	public int getNbrDisLikeByPost(Long id);
}
