package tn.esprit.vbank.services;

import java.util.List;

import tn.esprit.vbank.entities.Carte;

public interface ICarteService {

	public Carte recupererCarte(Long id);

	public List<Carte> listCartes();

	public Carte ajouterCarte(Carte carte);

	public Carte modifierCarte(Carte carte);

	public void supprimerCarte(Long id);

	public void affecterCarteAbonnement(Long idCarte, Long idCompte, Long idUser);

}
