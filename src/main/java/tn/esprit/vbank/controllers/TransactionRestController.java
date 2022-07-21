package tn.esprit.vbank.controllers;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import tn.esprit.vbank.entities.Compte;
import tn.esprit.vbank.entities.Report;
import tn.esprit.vbank.entities.Transaction;
import tn.esprit.vbank.enums.TypeIdentiteClient;
import tn.esprit.vbank.enums.TypeTransaction;
import tn.esprit.vbank.services.ICompteService;
import tn.esprit.vbank.services.ITransactionService;
import tn.esprit.vbank.utils.InputValidator;
import tn.esprit.vbank.utils.RecuPDFExporter;
import tn.esprit.vbank.utils.TransactionInput;
import tn.esprit.vbank.utils.TransactionUtils;
import tn.esprit.vbank.utils.dto.Recu;

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
	
	@GetMapping("/getTransactionsDuCompte/{id}")
	public List<Transaction> getTransactionsByCompteId(@PathVariable Long id) {
		List<Transaction> result = transactionService.getTransactionsDuCompte(id);
		return result;
	}
	
	@RequestMapping(value = "/faireOperation", method = RequestMethod.POST, produces = {"application/pdf"})
	@ResponseBody
	public ResponseEntity creerTransaction(@RequestBody TransactionInput input, HttpServletResponse response) throws DocumentException, IOException {
		if(!InputValidator.isInputValid(input))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + new Timestamp(System.currentTimeMillis()).toGMTString() + ".pdf";
        response.setHeader(headerKey, headerValue);
		Transaction transaction = new Transaction();
		transaction.setNomClient(input.getNomClient());
		transaction.setTypeIdentiteClient(TypeIdentiteClient.valueOf(input.getTypeIdentiteClient().toUpperCase()));
		transaction.setNumeroIdentiteClient(input.getNumeroIdentiteClient());
		transaction.setMontant(input.getMontant());
		transaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
		transaction.setReference(TransactionUtils.generateTransactionReference());
		if(input.getCompteSource().equals("0")) {
			Optional<Compte> compteDestinataire = compteService.getCompteById(Long.valueOf(input.getCompteDestinataire()));
			if(!compteDestinataire.isPresent())
				return new ResponseEntity<>("Compte destinataire inexistant !", HttpStatus.BAD_REQUEST);
			transaction.setTypeTransaction(TypeTransaction.DEPOT);
			Compte compte = compteDestinataire.get();
			transaction.setCompteDestinataire(compte);
			transaction.setCompteDestinataireId(compte.getCompteId());
			transaction.setCompteSource(null);
			compte.setSolde(compte.getSolde() + input.getMontant());
			compteService.modifierCompte(compte);
			Recu recu = new Recu(TypeTransaction.DEPOT.toString(), "", transaction.getCompteDestinataire().getNumeroCompte(), 
				transaction.getNomClient(), transaction.getTypeIdentiteClient().toString(), transaction.getNumeroIdentiteClient(), 
				transaction.getNomDestinataire(), Double.toString(transaction.getMontant()), transaction.getTimestamp().toGMTString(), transaction.getReference());
			
			RecuPDFExporter exporter = new RecuPDFExporter(recu);
			transactionService.createOrUpdateTransaction(transaction);
			exporter.export(response);
		} else if(input.getCompteDestinataire().equals("0")) {
			Optional<Compte> compteSource = compteService.getCompteById(Long.valueOf(input.getCompteSource()));
			if(!compteSource.isPresent())
				return new ResponseEntity<>("Compte source inexistant !", HttpStatus.BAD_REQUEST);
			transaction.setTypeTransaction(TypeTransaction.RETRAIT);
			Compte compte = compteSource.get();
			transaction.setCompteSource(compte);
			transaction.setCompteSourceId(compte.getCompteId());
			transaction.setCompteDestinataire(null);
			compte.setSolde(compte.getSolde() - input.getMontant());
			compteService.modifierCompte(compte);
			Recu recu = new Recu(TypeTransaction.DEPOT.toString(), transaction.getCompteSource().getNumeroCompte(), "", 
					transaction.getNomClient(), transaction.getTypeIdentiteClient().toString(), transaction.getNumeroIdentiteClient(), 
					"", Double.toString(transaction.getMontant()), transaction.getTimestamp().toGMTString(), transaction.getReference());
				
			RecuPDFExporter exporter = new RecuPDFExporter(recu);
			transactionService.createOrUpdateTransaction(transaction);
			exporter.export(response);

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
			transaction.setCompteSourceId(source.getCompteId());
			transaction.setCompteDestinataire(dest);
			transaction.setCompteDestinataireId(dest.getCompteId());
			source.setSolde(source.getSolde() - input.getMontant());
			dest.setSolde(dest.getSolde() + input.getMontant());
			compteService.modifierCompte(source);
			compteService.modifierCompte(dest);
			Recu recu = new Recu(TypeTransaction.DEPOT.toString(), transaction.getCompteSource().getNumeroCompte(), transaction.getCompteDestinataire().getNumeroCompte(), 
					transaction.getNomClient(), transaction.getTypeIdentiteClient().toString(), transaction.getNumeroIdentiteClient(), 
					transaction.getNomDestinataire(), Double.toString(transaction.getMontant()), transaction.getTimestamp().toGMTString(), transaction.getReference());
				
			RecuPDFExporter exporter = new RecuPDFExporter(recu);
			transactionService.createOrUpdateTransaction(transaction);
			exporter.export(response);	
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/effacerTransaction/{id}")
	public ResponseEntity effacerTransaction(@PathVariable Long id) {
		return new ResponseEntity<>("Transaction supprimee avec succes : " + id, HttpStatus.OK);
	}
	
/*	@PostMapping(value = "/saveReport")
	public ResponseEntity saveReport(@RequestBody Report report) {
	}
	*/
	
	@GetMapping("/generateReport")
	public Report generateReport(@RequestParam(value = "Compte") long compteId, @RequestParam(value = "Date debut") String dateDebut,
			@RequestParam(value = "Date fin") String dateFin) {
		Optional<Compte> compteOpt = compteService.getCompteById(Long.valueOf(compteId));
	//	if(!compteOpt.isPresent())
//			return new ResponseEntity<>("Compte source inexistant !", HttpStatus.BAD_REQUEST);
		Report report = new Report();
		Compte compte = compteOpt.get();
		report.setNomClient(compte.getPropiétaire());
		report.setNumCompte(compte);
		//create service & repo of report and get transactions by dates and format dates to timestamps
		return report;
	}
	
	//getReportById
	
}
