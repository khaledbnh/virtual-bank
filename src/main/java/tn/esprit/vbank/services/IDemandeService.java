package tn.esprit.vbank.services;

import java.util.List;

import tn.esprit.vbank.entities.Demande;

public interface IDemandeService {
	
	List<Demande> retrieveAllDemands(); 
	Demande addDemande(Demande u);
	void deleteDemande(String id);
	Demande updateDemande(Demande u);
	Demande retrieveDemande(String id);

}
