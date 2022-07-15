package tn.esprit.vbank.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.vbank.entities.Abonnement;
import tn.esprit.vbank.repositories.AbonnementRepository;
import tn.esprit.vbank.services.IAbonnementService;

@Service
public class AbonnementServiceImpl implements IAbonnementService {

	@Autowired
	private AbonnementRepository abonnementRepository;

	@Override
	public Abonnement recupererAbonnement(Long id) {
		return abonnementRepository.findById(id).get();
	}

	@Override
	public List<Abonnement> listAbonnements() {
		return (List<Abonnement>) abonnementRepository.findAll();
	}

	@Override
	public Abonnement ajouterAbonnement(Abonnement abonnement) {
		return abonnementRepository.save(abonnement);
	}

	@Override
	public Abonnement modifierAbonnement(Abonnement abonnement) {
		return abonnementRepository.save(abonnement);
	}

	@Override
	public void supprimerAbonnement(Long id) {
		abonnementRepository.deleteById(id);
	}

}
