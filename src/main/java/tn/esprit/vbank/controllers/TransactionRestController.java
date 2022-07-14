package tn.esprit.vbank.controllers;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
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
	
	@PostMapping("/faireOperation")
	public ResponseEntity creerTransaction(@RequestBody TransactionInput input) {
		if(!InputValidator.isInputValid(input))
			return new ResponseEntity<>("Invalid input !", HttpStatus.BAD_REQUEST);
		Transaction transaction = new Transaction();
		transaction.setNomClient(input.getNomClient());
		transaction.setTypeIdentiteClient(TypeIdentiteClient.valueOf(input.getTypeIdentiteClient()));
		transaction.setNumeroIdentiteClient(input.getNumeroIdentiteClient());
		if(input.getCompteSource().equals("0")) {
			Compte compteDestinataire = compteService.getCompteById(Long.valueOf(input.getCompteDestinataire()));
			if(compteDestinataire == null)
				return new ResponseEntity<>("Compte destinataire inexistant !", HttpStatus.BAD_REQUEST);
			transaction.setTypeTransaction(TypeTransaction.RETRAIT);
			transaction.setCompteDestinataire(compteDestinataire);
			transaction.setCompteSource(null);
		} else if(input.getCompteDestinataire().equals("0")) {
			Compte compteSource = compteService.getCompteById(Long.valueOf(input.getCompteSource()));
			if(compteSource == null)
				return new ResponseEntity<>("Compte source inexistant !", HttpStatus.BAD_REQUEST);
			transaction.setTypeTransaction(TypeTransaction.DEPOT);
			transaction.setCompteSource(compteSource);
			transaction.setCompteDestinataire(null);
		} else {
			Compte compteSource = compteService.getCompteById(Long.valueOf(input.getCompteSource()));
			if(compteSource == null)
				return new ResponseEntity<>("Compte source inexistant !", HttpStatus.BAD_REQUEST);
			Compte compteDestinataire = compteService.getCompteById(Long.valueOf(input.getCompteDestinataire()));
			if(compteSource == null)
				return new ResponseEntity<>("Compte destinataire inexistant !", HttpStatus.BAD_REQUEST);
			transaction.setTypeTransaction(TypeTransaction.VIREMENT);
			transaction.setCompteSource(compteSource);
			transaction.setCompteDestinataire(compteDestinataire);
		}
		

		transaction.setMontant(input.getMontant());
		transaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
		transaction.setReference(TransactionUtils.generateTransactionReference());
		transactionService.createOrUpdateTransaction(transaction);
		return new ResponseEntity<>("Entite cree avec succes : " + transaction.toString(), HttpStatus.OK);
	}
	
	@DeleteMapping("/effacerTransaction/{id}")
	public ResponseEntity effacerTransaction(@PathVariable Long id) {
		return new ResponseEntity<>("Entite supprimee avec succes : " + id, HttpStatus.OK);
	}
}
