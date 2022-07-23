package tn.esprit.vbank.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.vbank.entities.Abonnement;
import tn.esprit.vbank.services.IAbonnementService;

@RestController
public class AbonnementRestController {

	@Autowired
	private IAbonnementService abonnementService;

	@GetMapping(value = "/recupererAbonnement/{id}")
	public ResponseEntity recupererAbonnement(@PathVariable Long id) {
		Abonnement abonnement = null;
		try {
			abonnement = abonnementService.recupererAbonnement(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(abonnement);
	}

	@GetMapping(value = "/listAbonnements")
	public ResponseEntity listAbonnements() {
		List<Abonnement> abonnements = new ArrayList<>();
		try {
			abonnements = abonnementService.listAbonnements();
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(abonnements);
	}

	@PostMapping(value = "/ajouterAbonnement")
	public ResponseEntity ajouterAbonnement(@RequestBody Abonnement abonnement) {
		Abonnement abonnementPostSave = null;
		try {
			abonnementPostSave = abonnementService.ajouterAbonnement(abonnement);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(abonnementPostSave);
	}

	@PutMapping(value = "/modifierAbonnement")
	public ResponseEntity updateAbonnement(@RequestBody Abonnement abonnement) {
		Abonnement abonnementPostSave = null;
		try {
			abonnementPostSave = abonnementService.modifierAbonnement(abonnement);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(abonnementPostSave);
	}

	@DeleteMapping(value = "/supprimerAbonnement/{id}")
	public ResponseEntity deleteAbonnement(@PathVariable Long id) {
		try {
			abonnementService.supprimerAbonnement(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Abonnement supprimer");
	}

	@PutMapping(value = "/affecterAbonnementCompte")
	public ResponseEntity affecterAbonnementCompte(@RequestParam("idCompte") Long idCompte,
			@RequestParam("idAbonnement") Long idAbonnement, @RequestParam("idUser") Long idUser) {
		try {
			abonnementService.affecterAbonnementCompte(idAbonnement, idCompte, idUser);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Abonnement affecter avec succées");
	}

}
