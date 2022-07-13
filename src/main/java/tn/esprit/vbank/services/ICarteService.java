package tn.esprit.vbank.services;

import java.util.List;

import tn.esprit.vbank.entities.Carte;

public interface ICarteService {

	Carte recupererCarte(Long id);

	List<Carte> listCartes();

	public Carte ajouterCarte(Carte carte);

	Carte modifierCarte(Carte carte);

	void supprimerCarte(Long id);

}
