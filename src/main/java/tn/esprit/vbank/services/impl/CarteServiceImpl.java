package tn.esprit.vbank.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.vbank.entities.Carte;
import tn.esprit.vbank.entities.Compte;
import tn.esprit.vbank.entities.Notification;
import tn.esprit.vbank.entities.User;
import tn.esprit.vbank.enums.TypeNotification;
import tn.esprit.vbank.repositories.AbonnementRepository;
import tn.esprit.vbank.repositories.CarteRepository;
import tn.esprit.vbank.repositories.CompteRepository;
import tn.esprit.vbank.repositories.UserRepository;
import tn.esprit.vbank.services.ICarteService;
import tn.esprit.vbank.services.INotificationService;
import tn.esprit.vbank.utils.CreditCardSampleGenerator;

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

	@Autowired
	private UserRepository userRepository;

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
	public void affecterCarteAbonnement(Long idCarte, Long idCompte, Long idUser) {
		String path = "C:\\Users\\kbenhamouda\\Desktop\\RES";
		String filePath = "C:\\Users\\kbenhamouda\\Desktop\\RES\\sample.pdf";
		Compte compte = compteRepository.findById(idCompte).get();
		Carte carte = carteRepository.findById(idCarte).get();
		carte.setUuid(generateUuid());
		carte.setCcv(generateCcv());
		compte.getAbonnement().getCartes().add(carte);
		compteRepository.save(compte);
		User user = userRepository.findById(idUser).get();
		Map<String, String> data = new HashMap<>();
		String name = user.getFirstName() + " " + user.getLastName();
		data.put("nom", name);
		data.put("type", carte.getType());
		data.put("frais", String.valueOf(carte.getFrais()));
		CreditCardSampleGenerator.generateCreditCardSample(name, carte, path);
		notificationService.notifier(new Notification(filePath, TypeNotification.AJOUT_CARTE, user), data);
	}

	private long generateUuid() {
		long leftLimit = 1000000000000000L;
		long rightLimit = 9999999999999999L;
		return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
	}

	private int generateCcv() {
		int leftLimit = 100;
		int rightLimit = 999;
		return leftLimit + (int) (Math.random() * (rightLimit - leftLimit));
	}

}
