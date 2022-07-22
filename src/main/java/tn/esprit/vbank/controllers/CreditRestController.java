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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.vbank.entities.Abonnement;
import tn.esprit.vbank.entities.Credit;
import tn.esprit.vbank.services.ICreditService;

@RestController

@RequestMapping("/credit")
public class CreditRestController {

	@Autowired
	private ICreditService creditService;

	@GetMapping("/retrieve-all-credits")
	public ResponseEntity retrieveAllCredits() {
		List<Credit> listCredit = new ArrayList<>();
		try {
			listCredit = creditService.retrieveAllCredits();
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}

		return ResponseEntity.status(HttpStatus.OK).body(listCredit);

	}

	@PostMapping("/add-credit")
	public ResponseEntity createCredit(@RequestBody Credit c) {

		Credit creditCreated = null;
		try {

			creditCreated = creditService.createCredit(c);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(creditCreated);
	}

	@GetMapping("/get-credit/{credit-id}")
	public ResponseEntity retrieveCredit(@PathVariable("credit-id") Long id) {
		Credit credit = null;
		try {
			credit = creditService.retrieveCredit(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(credit);
	}

	@DeleteMapping("/remove-credit/{credit-id}")
	public ResponseEntity deleteCredit(@PathVariable("credit-id") Long creditId) {
		try {
			creditService.deleteCredit(creditId);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}

		return ResponseEntity.status(HttpStatus.OK).body("credit supprimé");
	}

	@PutMapping(value = "/update-credit/{credit-id}")
	public ResponseEntity updateCredit(@RequestBody Credit cr, @PathVariable("credit-id") Long id) {
		Credit credit = null;
		try {
			credit = creditService.updateCredit(cr, id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(credit);
	}

	@PostMapping("/calculcreditimm/{compte-id}")
	public ResponseEntity calculCredit(@RequestBody Credit c, @PathVariable("compte-id") Long idCompte) {

		Credit credit = null;
		try {
			credit = creditService.createCredit(c, idCompte);

		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(credit);
	}

	@PutMapping("/affectercredit/{id-credit}")
	public ResponseEntity Affectercredit(@PathVariable("id-credit") Long idCredit) {

		Credit credit = null;
		try {
			credit = creditService.affectercredit(idCredit);

		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(credit);
	}

	@PostMapping("/calculcreditdeux/{compte-id}")
	public ResponseEntity calculCreditdeux(@RequestBody Credit cr, @PathVariable("compte-id") Long idCompte) {

		Credit credit = null;
		try {
			credit = creditService.Createothercredit(cr, idCompte);

		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(credit);
	}

}
