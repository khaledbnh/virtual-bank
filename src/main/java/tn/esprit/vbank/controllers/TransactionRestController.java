package tn.esprit.vbank.controllers;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.vbank.entities.Compte;
import tn.esprit.vbank.entities.Transaction;
import tn.esprit.vbank.enums.TypeIdentiteClient;
import tn.esprit.vbank.enums.TypeTransaction;
import tn.esprit.vbank.services.ICompteService;
import tn.esprit.vbank.services.ITransactionService;
import tn.esprit.vbank.utils.InputValidator;
import tn.esprit.vbank.utils.TransactionInput;
import tn.esprit.vbank.utils.TransactionUtils;

@RestController
public class TransactionRestController {
	
	@Autowired 
	ITransactionService transactionService;
	
	@Autowired 
	ICompteService compteService;
	
 
	@GetMapping("/getAllTransactions")
	public List<Transaction> getAllTransactions() {
		List<Transaction> result = transactionService.getAllTransactions();
		return result;
	}
	
	@GetMapping("/getTransactionsDuCompte")
	public List<Transaction> getTransactionsByCompteId() {
		List<Transaction> result = transactionService.getAllTransactions();
		return result;
	}
	
	@PostMapping("/faireOperation")
	public ResponseEntity creerTransaction(@RequestBody TransactionInput input) {
		if(!InputValidator.isInputValid(input))
			return new ResponseEntity<>("Invalid input !", HttpStatus.BAD_REQUEST);
		Transaction transaction = new Transaction();
		transaction.setNomClient(input.getNomClient());
		transaction.setTypeIdentiteClient(TypeIdentiteClient.valueOf(input.getTypeIdentiteClient().toUpperCase()));
		transaction.setNumeroIdentiteClient(input.getNumeroIdentiteClient());
		if(input.getCompteSource().equals("0")) {
			Optional<Compte> compteDestinataire = compteService.getCompteById(Long.valueOf(input.getCompteDestinataire()));
			if(!compteDestinataire.isPresent())
				return new ResponseEntity<>("Compte destinataire inexistant !", HttpStatus.BAD_REQUEST);
			transaction.setTypeTransaction(TypeTransaction.RETRAIT);
			Compte compte = compteDestinataire.get();
			transaction.setCompteDestinataire(compte);
			transaction.setCompteSource(null);
			compte.setSolde(compte.getSolde() - input.getMontant());
			compteService.modifierCompte(compte);
		} else if(input.getCompteDestinataire().equals("0")) {
			Optional<Compte> compteSource = compteService.getCompteById(Long.valueOf(input.getCompteSource()));
			if(!compteSource.isPresent())
				return new ResponseEntity<>("Compte source inexistant !", HttpStatus.BAD_REQUEST);
			transaction.setTypeTransaction(TypeTransaction.DEPOT);
			Compte compte = compteSource.get();
			transaction.setCompteSource(compte);
			transaction.setCompteDestinataire(null);
			compte.setSolde(compte.getSolde() + input.getMontant());
			compteService.modifierCompte(compte);
		} else {
			Optional<Compte> compteSource = compteService.getCompteById(Long.valueOf(input.getCompteSource()));
			if(!compteSource.isPresent())
				return new ResponseEntity<>("Compte source inexistant !", HttpStatus.BAD_REQUEST);
			Optional<Compte> compteDestinataire = compteService.getCompteById(Long.valueOf(input.getCompteDestinataire()));
			if(!compteDestinataire.isPresent())
				return new ResponseEntity<>("Compte destinataire inexistant !", HttpStatus.BAD_REQUEST);
			transaction.setTypeTransaction(TypeTransaction.VIREMENT);
			Compte source = compteSource.get();
			Compte dest = compteDestinataire.get();
			transaction.setCompteSource(source);
			transaction.setCompteDestinataire(dest);
			source.setSolde(source.getSolde() - input.getMontant());
			dest.setSolde(dest.getSolde() + input.getMontant());
			compteService.modifierCompte(source);
			compteService.modifierCompte(dest);
		}
		

		transaction.setMontant(input.getMontant());
		transaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
		transaction.setReference(TransactionUtils.generateTransactionReference());
		transactionService.createOrUpdateTransaction(transaction);
		return new ResponseEntity<>("Operation fini avec succes : "
				+ "Reference transaction : " + transaction.getReference(), HttpStatus.OK);	
	}
	
	@DeleteMapping("/effacerTransaction/{id}")
	public ResponseEntity effacerTransaction(@PathVariable Long id) {
		return new ResponseEntity<>("Entite supprimee avec succes : " + id, HttpStatus.OK);
	}
}
