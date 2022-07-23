package tn.esprit.vbank.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.vbank.entities.Abonnement;
import tn.esprit.vbank.entities.Compte;
import tn.esprit.vbank.entities.Notification;
import tn.esprit.vbank.entities.User;
import tn.esprit.vbank.enums.TypeNotification;
import tn.esprit.vbank.repositories.AbonnementRepository;
import tn.esprit.vbank.repositories.CompteRepository;
import tn.esprit.vbank.repositories.UserRepository;
import tn.esprit.vbank.services.IAbonnementService;
import tn.esprit.vbank.services.INotificationService;

@Service
public class AbonnementServiceImpl implements IAbonnementService {

	@Autowired
	private AbonnementRepository abonnementRepository;

	@Autowired
	private CompteRepository compteRepository;

	@Autowired
	private INotificationService notificationService;

	@Autowired
	private UserRepository userRepository;

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

	@Override
	public void affecterAbonnementCompte(Long idAbonnement, Long idCompte, Long idUser) {
		Compte compte = compteRepository.findById(idCompte).get();
		User user = userRepository.findById(idUser).get();
		Abonnement abonnement = abonnementRepository.findById(idAbonnement).get();
		compte.setAbonnement(abonnement);
		compte = compteRepository.save(compte);
		Map<String, String> data = new HashMap<>();
		data.put("nom", user.getFirstName() + " " + user.getLastName());
		data.put("abonnement", abonnement.getNom());
		data.put("frais", String.valueOf(abonnement.getFrais()));
		notificationService.notifier(new Notification("", TypeNotification.AFFECTATION_ABONNEMENT, user), data);
	}
}
