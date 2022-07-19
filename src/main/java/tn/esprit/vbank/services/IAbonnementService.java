package tn.esprit.vbank.services;

import java.util.List;

import tn.esprit.vbank.entities.Abonnement;

public interface IAbonnementService {

	Abonnement recupererAbonnement(Long id);

	List<Abonnement> listAbonnements();

	public Abonnement ajouterAbonnement(Abonnement abonnement);

	Abonnement modifierAbonnement(Abonnement abonnement);

	void supprimerAbonnement(Long id);
	
}
