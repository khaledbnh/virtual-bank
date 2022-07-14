package tn.esprit.vbank.services;

import java.util.List;
import java.util.Optional;

import tn.esprit.vbank.entities.Compte;

public interface ICompteService {

	Optional<Compte> getCompteById(Long id);

	List<Compte> listComptes();

	public Compte creerCompte(Compte compte);

	Compte modifierCompte(Compte compte);

	void supprimerCompte(Long id);
}
