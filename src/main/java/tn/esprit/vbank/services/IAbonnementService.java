package tn.esprit.vbank.services;

import java.util.List;

import tn.esprit.vbank.entities.Abonnement;

public interface IAbonnementService {

	public Abonnement recupererAbonnement(Long id);

	public List<Abonnement> listAbonnements();

	public Abonnement ajouterAbonnement(Abonnement abonnement);

	public Abonnement modifierAbonnement(Abonnement abonnement);

	public void supprimerAbonnement(Long id);

	public void affecterAbonnementCompte(Long idAbonnement, Long idCompte, Long idUser);
	
}
