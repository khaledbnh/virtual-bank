package tn.esprit.vbank.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.vbank.entities.Comment;
import tn.esprit.vbank.entities.Post;
import tn.esprit.vbank.entities.User;
import tn.esprit.vbank.services.ICommentService;
import tn.esprit.vbank.services.IPostService;
import tn.esprit.vbank.services.IUserService;
import tn.esprit.vbank.utils.PostValidator;

@RestController
public class CommentRestController {

	@Autowired
	private ICommentService iCommentService;

	@Autowired
	private IUserService iUserService;

	@Autowired
	private IPostService iPostService;

	@PostMapping("/ajouterComment")
	@ResponseBody
	public ResponseEntity ajouterComment(@RequestBody Comment a) {

		if (a.getContent() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't comment a post with null content");
		}
		if (a.getUser() == null || iUserService.getUser(a.getUser().getId()) == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user not found");

		}
		if ((a.getPost() == null || iPostService.getPostById(a.getPost().getIdPost()) == null)
				&& (a.getParentComment() == null
						|| iCommentService.getCommentById(a.getParentComment().getIdComment()) == null)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("post or parent comment not found");

		}

		try {
			String filtredComment = PostValidator.filterBadWorlds(a.getContent());
			a.setContent(filtredComment);

		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid data");

		}
		a.setDateCreation(new Date());
		Comment comment = null;
		try {
			comment = iCommentService.addComment(a);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(comment);

	}

	@RequestMapping(value = "/Comments", method = RequestMethod.GET)
	public List<Comment> getComments() {

		return iCommentService.getComments();
	}

	@RequestMapping(value = "/supprimerComment/{id}", method = RequestMethod.DELETE)
	public ResponseEntity supprimerComment(@PathVariable Long id) {

		Boolean delete = false;
		try {
			delete = iCommentService.supprimerComment(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		if (delete) {
			return ResponseEntity.status(HttpStatus.OK).body("comment deleted");

		}
		return ResponseEntity.status(HttpStatus.OK).body("comment not found");

	}

	@RequestMapping(value = "/modiferComment/{id}", method = RequestMethod.PUT)
	public ResponseEntity updateContact(@PathVariable Long id, @RequestBody Comment a) {
		
		if (a.getContent() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't comment a post with null content");
		}
		if (a.getUser() == null || iUserService.getUser(a.getUser().getId()) == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user not found");

		}
		if ((a.getPost() == null || iPostService.getPostById(a.getPost().getIdPost()) == null)
				&& (a.getParentComment() == null
						|| iCommentService.getCommentById(a.getParentComment().getIdComment()) == null)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("post or parent comment not found");

		}

		try {
			String filtredComment = PostValidator.filterBadWorlds(a.getContent());
			a.setContent(filtredComment);

		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid data");

		}
		Comment comment = null;
		try {
			comment = iCommentService.updateComment(id, a);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}		
		
		return ResponseEntity.status(HttpStatus.OK).body(comment);

	}

	@RequestMapping(value = "/CommentByid/{id}", method = RequestMethod.GET)
	public ResponseEntity getCommentById(@PathVariable Long id) {

		Comment comment = null;
		try {
			comment = iCommentService.getCommentById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		if (comment != null) {
			return ResponseEntity.status(HttpStatus.OK).body(comment);

		}
		return ResponseEntity.status(HttpStatus.OK).body("comment not found");
	}

	@RequestMapping(value = "/getCommentsByComment/{id}", method = RequestMethod.GET)
	public Set<Comment> getCommentsByPost(@PathVariable Long id) {

		Comment comment = iCommentService.getCommentById(id);
		if (comment != null) {
			return comment.getSubComments();

		}
		return null;
	}

	@RequestMapping(value = "/nbeCommentsByComment/{id}", method = RequestMethod.GET)
	public int getnbeCommentsByPost(@PathVariable Long id) {

		int nbe = 0;

		Comment comment = iCommentService.getCommentById(id);
		if (comment != null) {
			nbe = comment.getSubComments().size();
		}

		return nbe;
	}
}
