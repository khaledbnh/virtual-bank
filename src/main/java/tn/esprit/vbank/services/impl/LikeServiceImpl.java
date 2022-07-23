package tn.esprit.vbank.services.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.vbank.entities.Like;
import tn.esprit.vbank.repositories.LikeRepository;
import tn.esprit.vbank.services.ILikeService;


@Service
public class LikeServiceImpl implements ILikeService {

	private static final long serialVersionUID = 6191889143079517027L;

	@Autowired
	private LikeRepository LikeRepository;
	
	@Override
	public List<Like> getLikes() {

		return (List<Like>) LikeRepository.findAll();
	}

	@Override
	public Like getLikeById(Long id) {
		return LikeRepository.findById(id).orElse(null);
	}

	@Override
	public Like addLike(Like a) {
		return LikeRepository.save(a);
	}

	@Override
	public boolean supprimerLike(Long id) {

		LikeRepository.deleteLikeById(id);
		return true;
	}

	@Override
	public Like updateLike(Long id, Like a) {

		return LikeRepository.save(a);
	}

	@Override
	public int getNbrLikeByPost(Long id) {
		return LikeRepository.getnbrLikeByPost(id);
	}

	@Override
	public int getNbrDisLikeByPost(Long id) {
		return LikeRepository.getnbrDisLikeByPost(id);

	}

	@Override
	public Like getLikeWithIdUserAndIdPost(Long idPost, Long idUser) {
	
		return LikeRepository.getLikeWithIdUserAndIdPost(idPost, idUser);
	}

}
