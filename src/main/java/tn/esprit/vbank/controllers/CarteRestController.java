package tn.esprit.vbank.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import tn.esprit.vbank.entities.Carte;
import tn.esprit.vbank.services.ICarteService;

@Controller
public class CarteRestController {

	@Autowired
	private ICarteService carteService;

	@GetMapping(value = "/recupererCarte/{id}")
	public ResponseEntity recupererCarte(@PathVariable Long id) {
		Carte carte = null;
		try {
			carte = carteService.recupererCarte(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(carte);
	}

	@GetMapping(value = "/listCartes")
	public ResponseEntity listCartes() {
		List<Carte> cartes = new ArrayList<>();
		try {
			cartes = carteService.listCartes();
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(cartes);
	}

	@PostMapping(value = "/ajouterCarte")
	public ResponseEntity ajouterCarte(@RequestBody Carte carte) {
		Carte cartePostSave = null;
		try {
			cartePostSave = carteService.ajouterCarte(carte);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(cartePostSave);
	}

	@PutMapping(value = "/modifierCarte")
	public ResponseEntity updateCarte(@RequestBody Carte carte) {
		Carte cartePostSave = null;
		try {
			cartePostSave = carteService.modifierCarte(carte);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(cartePostSave);
	}

	@DeleteMapping(value = "/supprimerCarte/{id}")
	public ResponseEntity deleteCarte(@PathVariable Long id) {
		try {
			carteService.supprimerCarte(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Carte supprimer");
	}
}
