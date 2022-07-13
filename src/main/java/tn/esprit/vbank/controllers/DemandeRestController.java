package tn.esprit.vbank.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import tn.esprit.vbank.entities.Demande;
import tn.esprit.vbank.services.IDemandeService;

public class DemandeRestController {
	
	
	
	
	//couplage faible
		@Autowired
		IDemandeService iDemandeservice;
		
		//afficher tous les demandes
		// URL : http://localhost:????/????/retrieve-all-demands
	 	 // http://localhost:8081/SpringMVC/servlet/getAllDemands

		@GetMapping("/retrieve-all-demands")
		public List<Demande> retrieveAllUsers() {
			List<Demande> list = iDemandeservice.retrieveAllDemands();
			return list;
		}
	 
		//afficher demande by id
		// http://localhost:????/vitual-bank/retrieve-demand/{demand-id}
		@GetMapping("/retrieve-demand/{demande-id}")
		public Demande retrieveDemande(@PathVariable("demande-id") String demandeId) {
			return iDemandeservice.retrieveDemande(demandeId);
		}

		// Ajouter Demande : http://localhost:????/vitual-bank/add-demande 
		@PostMapping("/add-demande")
		public Demande addUser(@RequestBody Demande d) {
			Demande demande = iDemandeservice.addDemande(d); 
			return demande;
		}

		
		// Supprimer demande  : 
		// http://localhost:????/vitual-bank/remove-demande/{demande-id}
		@DeleteMapping("/remove-demande/{demande-id}") 
		public void removedDemande(@PathVariable("demande-id") String demandeId) { 
			iDemandeservice.deleteDemande(demandeId);
		} 

		// Modifier demande 
		// http://localhost:????/vitual-bank/modify-demande
		@PutMapping("/modify-demande") 
		public Demande updateDemande(@RequestBody Demande demande) {
			return iDemandeservice.updateDemande(demande);
		}
		 

}
