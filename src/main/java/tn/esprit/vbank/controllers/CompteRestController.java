package tn.esprit.vbank.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.vbank.entities.Compte;
import tn.esprit.vbank.services.ICompteService;

@RestController
public class CompteRestController {

	@Autowired
	private ICompteService compteService;

	@GetMapping(value = "/getCompte/{id}")
	public ResponseEntity getCompte(@PathVariable Long id) {
		Optional<Compte> compte = compteService.getCompteById(id);
		if(!compte.isPresent())
		return ResponseEntity.badRequest().body("Compte inexistant");
		return ResponseEntity.status(HttpStatus.OK).body(compte.get());
	}

	@GetMapping(value = "/listComptes")
	public ResponseEntity listComptes() {
		List<Compte> comptes = new ArrayList<>();
		try {
			comptes = compteService.listComptes();
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(comptes);
	}

	@PostMapping(value = "/creerCompte")
	public ResponseEntity creerCompte(@RequestBody Compte compte) {
		Compte comptePostSave = null;
		try {
			comptePostSave = compteService.creerCompte(compte);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(comptePostSave);
	}

	@PutMapping(value = "/modifierCompte")
	public ResponseEntity updateCompte(@RequestBody Compte compte) {
		Compte comptePostSave = null;
		try {
			comptePostSave = compteService.modifierCompte(compte);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(comptePostSave);
	}

	@DeleteMapping(value = "/supprimerCompte/{id}")
	public ResponseEntity deleteCarte(@PathVariable Long id) {
		try {
			compteService.supprimerCompte(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Compte supprimee");
	}
}
