package tn.esprit.vbank.services.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tn.esprit.vbank.entities.Demande;
import tn.esprit.vbank.repositories.DemandeRepository;
import tn.esprit.vbank.services.IDemandeService;

public class DemandeServiceImpl implements IDemandeService {
	@Autowired
	DemandeRepository demandeRepository;

	private static final Logger l = LogManager.getLogger(DemandeServiceImpl.class);
 	
	@Override
	public List<Demande> retrieveAllDemands() { 
		List<Demande> demandes = null; 
		try {
			
			
			demandes = (List<Demande>) demandeRepository.findAll(); 
		
			
		}catch (Exception e) {
			l.error("Out of Method retrieveAllDemandes with Errors : " + e); 
		}

		return demandes;
	}


	@Override
	public Demande addDemande(Demande d) {
		
		Demande d_saved = null; 
		
		try {
			
			d_saved = demandeRepository.save(d); 
			
		} catch (Exception e) {
		}
		
		return d_saved; 
	}

	@Override 
	public Demande updateDemande(Demande d) {
		
		Demande demandeUpdated = null; 
		
		try {
			demandeUpdated = demandeRepository.save(d); 
			
		} catch (Exception e) {
		}
		
		return demandeUpdated; 
	}

	@Override
	public void deleteDemande(String id) {
		
		try {
			demandeRepository.deleteById(Long.parseLong(id)); 
			
		} catch (Exception e) {
		}
		
	}

	@Override
	public Demande retrieveDemande(String id) {
		Demande d = null; 
		try {
			d =  demandeRepository.findById(Long.parseLong(id)).get(); 
			
		} catch (Exception e) {
		}

		return d; 
	}
	
	

}
