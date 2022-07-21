package tn.esprit.vbank.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.vbank.entities.Comment;
import tn.esprit.vbank.repositories.CommentRepository;
import tn.esprit.vbank.services.ICommentService;

@Service
public class CommentServiceImpl implements ICommentService {

	private static final long serialVersionUID = 6191889143079517027L;

	@Autowired
	private CommentRepository CommentRepository;

	@Override
	public List<Comment> getComments() {

		return (List<Comment>) CommentRepository.findAll();
	}

	@Override
	public Comment getCommentById(Long id) {
		return CommentRepository.findById(id).orElse(null);
	}

	@Override
	public Comment addComment(Comment a) {

		return CommentRepository.save(a);
	}

	@Override
	public boolean supprimerComment(Long id) {

		try {
			CommentRepository.deleteById(id);

		} catch (Exception e) {
			return false;
		}

		return true;
	}

	@Override
	public Comment updateComment(Long id, Comment a) {

		a.setIdComment(id);
		return CommentRepository.save(a);
	}

}
