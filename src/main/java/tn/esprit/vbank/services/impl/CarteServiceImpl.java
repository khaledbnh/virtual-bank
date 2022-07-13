package tn.esprit.vbank.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.vbank.entities.Carte;
import tn.esprit.vbank.repositories.CarteRepository;
import tn.esprit.vbank.services.ICarteService;

@Service
public class CarteServiceImpl implements ICarteService {

	@Autowired
	private CarteRepository carteRepository;

	@Override
	public Carte recupererCarte(Long id) {
		return carteRepository.findById(id).get();
	}

	@Override
	public List<Carte> listCartes() {
		return (List<Carte>) carteRepository.findAll();
	}

	@Override
	public Carte ajouterCarte(Carte carte) {
		return carteRepository.save(carte);
	}

	@Override
	public Carte modifierCarte(Carte carte) {
		return carteRepository.save(carte);
	}

	@Override
	public void supprimerCarte(Long id) {
		carteRepository.deleteById(id);
	}

}
