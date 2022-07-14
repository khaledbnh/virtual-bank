package tn.esprit.vbank.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.vbank.entities.Compte;
import tn.esprit.vbank.repositories.CompteRepository;
import tn.esprit.vbank.services.ICompteService;

@Service
public class CompteServiceImpl implements ICompteService {

	@Autowired
	private CompteRepository compteRepository;

	@Override
	public Optional<Compte> getCompteById(Long id) {
		return compteRepository.findById(id);
	}

	@Override
	public List<Compte> listComptes() {
		return (List<Compte>) compteRepository.findAll();
	}

	@Override
	public Compte creerCompte(Compte compte) {
		return compteRepository.save(compte);
	}

	@Override
	public Compte modifierCompte(Compte compte) {
		return compteRepository.save(compte);
	}

	@Override
	public void supprimerCompte(Long id) {
		compteRepository.deleteById(id);
	}

	
}
