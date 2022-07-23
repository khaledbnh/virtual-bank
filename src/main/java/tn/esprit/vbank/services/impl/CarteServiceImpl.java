package tn.esprit.vbank.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.vbank.entities.Abonnement;
import tn.esprit.vbank.entities.Carte;
import tn.esprit.vbank.entities.Compte;
import tn.esprit.vbank.entities.Notification;
import tn.esprit.vbank.enums.TypeNotification;
import tn.esprit.vbank.repositories.AbonnementRepository;
import tn.esprit.vbank.repositories.CarteRepository;
import tn.esprit.vbank.repositories.CompteRepository;
import tn.esprit.vbank.services.ICarteService;
import tn.esprit.vbank.services.INotificationService;

@Service
public class CarteServiceImpl implements ICarteService {

	@Autowired
	private CarteRepository carteRepository;

	@Autowired
	private AbonnementRepository abonnementRepository;

	@Autowired
	private CompteRepository compteRepository;

	@Autowired
	private INotificationService notificationService;

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

	@Override
	public void affecterCarteAbonnement(Long idCarte, Long idAbonnement, Long idCompte) {
		Abonnement abonnement = abonnementRepository.findById(idAbonnement).get();
		Carte carte = carteRepository.findById(idCarte).get();
		abonnement.getCartes().add(carte);
		abonnementRepository.save(abonnement);
		Compte compte = compteRepository.findById(idCompte).get();
		Map<String, String> data = new HashMap<>();
		data.put("nom", compte.getClient().getNom());
		data.put("type", carte.getType());
		data.put("frais", String.valueOf(carte.getFrais()));
		notificationService.notifier(new Notification("", TypeNotification.AFFECTATION_ABONNEMENT, compte.getClient()),
				data);
	}

}
