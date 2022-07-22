package tn.esprit.vbank.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
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

import com.lowagie.text.DocumentException;

import tn.esprit.vbank.entities.Signal;
import tn.esprit.vbank.entities.Like;
import tn.esprit.vbank.entities.Post;
import tn.esprit.vbank.entities.User;
import tn.esprit.vbank.services.ISignalService;
import tn.esprit.vbank.services.IPostService;
import tn.esprit.vbank.services.IUserService;
import tn.esprit.vbank.utils.PostValidator;
import tn.esprit.vbank.utils.SignalPDFExporter;

@RestController
public class SignalRestController {

	@Autowired
	private ISignalService iSignalService;

	@Autowired
	private IUserService iUserService;

	@Autowired
	private IPostService iPostService;

	@PostMapping("/ajouterSignal")
	@ResponseBody
	public ResponseEntity ajouterSignal(@RequestBody Signal a) {

		if (a.getContent() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't Signal a post with null content");
		}
		if (a.getSignalType() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't Signal a post with null type");
		}
		if (a.getUser() == null || iUserService.getUser(a.getUser().getId()) == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user not found");

		}
		if ((a.getPost() == null || iPostService.getPostById(a.getPost().getIdPost()) == null)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("post  not found");

		}
		Signal existSignal=iSignalService.getSignalWithIdUserAndIdPost(a.getPost().getIdPost(), a.getUser().getId());
		if(existSignal!=null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user has already signaled this post");

		}
		//try {
			//String filtredSignal = PostValidator.filterBadWorlds(a.getContent());
			//a.setContent(filtredSignal);

		//} catch (IOException e) {
		//	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid data");

		//}
		a.setDateCreation(new Date());
		Signal Signal = null;
		try {
			Signal = iSignalService.addSignal(a);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		Post post=iPostService.getPostById(Signal.getPost().getIdPost());
		if(post.getListSignal().size()>10) {
			
			Set<Signal> listSignals = post.getListSignal();
	         
	        SignalPDFExporter exporter = new SignalPDFExporter(listSignals);
	        try {
				exporter.export(null);
			} catch (DocumentException | IOException | URISyntaxException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid data");
			}
			//generate pdf to admin
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(Signal);

	}

	@RequestMapping(value = "/Signals", method = RequestMethod.GET)
	public List<Signal> getSignals() {

		return iSignalService.getSignals();
	}

	@RequestMapping(value = "/supprimerSignal/{id}", method = RequestMethod.DELETE)
	public ResponseEntity supprimerSignal(@PathVariable Long id) {

		Boolean delete = false;
		try {
			delete = iSignalService.supprimerSignal(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		if (delete) {
			return ResponseEntity.status(HttpStatus.OK).body("Signal deleted");

		}
		return ResponseEntity.status(HttpStatus.OK).body("Signal not found");

	}

	@RequestMapping(value = "/modiferSignal/{id}", method = RequestMethod.PUT)
	public ResponseEntity updateContact(@PathVariable Long id, @RequestBody Signal a) {
		
		if (a.getContent() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't Signal a post with null content");
		}
		if (a.getUser() == null || iUserService.getUser(a.getUser().getId()) == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user not found");

		}
		if ((a.getPost() == null || iPostService.getPostById(a.getPost().getIdPost()) == null)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("post  not found");

		}

		try {
			String filtredSignal = PostValidator.filterBadWorlds(a.getContent());
			a.setContent(filtredSignal);

		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid data");

		}
		Signal Signal = null;
		try {
			Signal = iSignalService.updateSignal(id, a);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}		
		
		return ResponseEntity.status(HttpStatus.OK).body(Signal);

	}

	@RequestMapping(value = "/SignalByid/{id}", method = RequestMethod.GET)
	public ResponseEntity getSignalById(@PathVariable Long id) {

		Signal Signal = null;
		try {
			Signal = iSignalService.getSignalById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		if (Signal != null) {
			return ResponseEntity.status(HttpStatus.OK).body(Signal);

		}
		return ResponseEntity.status(HttpStatus.OK).body("Signal not found");
	}

	

	
}
