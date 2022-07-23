package tn.esprit.vbank.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

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

import com.lowagie.text.DocumentException;

import tn.esprit.vbank.entities.Like;
import tn.esprit.vbank.entities.Post;
import tn.esprit.vbank.entities.TLike;
import tn.esprit.vbank.entities.User;
import tn.esprit.vbank.services.ILikeService;
import tn.esprit.vbank.services.IPostService;
import tn.esprit.vbank.services.IUserService;
import tn.esprit.vbank.utils.StaticticJob;

@RestController
public class LikeRestController {

	@Autowired
	private ILikeService iLikeService;

	@Autowired
	private IUserService iUserService;

	@Autowired
	private IPostService iPostService;

	@PostMapping("/LikePost")
	@ResponseBody
	public ResponseEntity ajouterLike(@RequestBody Like a) {

		if (a.getTLike() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TLIKE NOT FOUND");
		}
		if (a.getUser() == null || iUserService.getUser(a.getUser().getId()) == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user not found");

		}
		if ((a.getPost() == null || iPostService.getPostById(a.getPost().getIdPost()) == null)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("post not found");

		}
		Like existLike=iLikeService.getLikeWithIdUserAndIdPost(a.getPost().getIdPost(), a.getUser().getId());
		if(existLike!=null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Like or dislike exist");

		}

		a.setDateCreation(new Date());
		Like Like = null;
		try {
			Like = iLikeService.addLike(a);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(Like);

	}

	@RequestMapping(value = "/Likes", method = RequestMethod.GET)
	public List<Like> getLikes() {
		try {
			StaticticJob StaticticJob=new StaticticJob();
			StaticticJob.export();
		} catch (DocumentException | IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return iLikeService.getLikes();
	}

	@RequestMapping(value = "/supprimerLike/{id}", method = RequestMethod.DELETE)
	public ResponseEntity supprimerLike(@PathVariable Long id) {
		
		Like Like = null;
		try {
			Like = iLikeService.getLikeById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		if (Like == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Like not found");

		}

		Boolean delete = false;
		try {
			delete = iLikeService.supprimerLike(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		if (delete) {
			return ResponseEntity.status(HttpStatus.OK).body("Like deleted");

		}
		return ResponseEntity.status(HttpStatus.OK).body("Like not found");

	}

	@RequestMapping(value = "/modiferLike/{id}", method = RequestMethod.PUT)
	public ResponseEntity updateContact(@PathVariable Long id, @RequestBody Like a) {
		
		if (a.getTLike() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TLIKE NOT FOUND");
		}
		if (a.getUser() == null || iUserService.getUser(a.getUser().getId()) == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user not found");

		}
		if ((a.getPost() == null || iPostService.getPostById(a.getPost().getIdPost()) == null)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("post not found");

		}
		
		
		Like Like = null;
		try {
			Like = iLikeService.updateLike(id, a);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}		
		
		return ResponseEntity.status(HttpStatus.OK).body(Like);

	}

	@RequestMapping(value = "/LikeByid/{id}", method = RequestMethod.GET)
	public ResponseEntity getLikeById(@PathVariable Long id) {

		Like Like = null;
		try {
			Like = iLikeService.getLikeById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		if (Like != null) {
			return ResponseEntity.status(HttpStatus.OK).body(Like);

		}
		return ResponseEntity.status(HttpStatus.OK).body("Like not found");
	}
	
	@RequestMapping(value = "/getNbrLikeByPost/{id}", method = RequestMethod.GET)
	public int getNbrLikeByPost(@PathVariable Long id) {

		return iLikeService.getNbrLikeByPost(id);
	}
	
	@RequestMapping(value = "/getNbrDisLikeByPost/{id}", method = RequestMethod.GET)
	public int getNbrDisLikeByPost(@PathVariable Long id) {

		return iLikeService.getNbrDisLikeByPost(id);
	}
}
