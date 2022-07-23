package tn.esprit.vbank.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import tn.esprit.vbank.entities.Compte;
import tn.esprit.vbank.repositories.CompteRepository;

@Component
public class ScheduledTask {

	@Autowired
	private CompteRepository compteRepository;
	
	@Scheduled(cron = "@monthly")
	public void monthlyScheduleTask() {
		Iterable<Compte> comptes = compteRepository.findAll();
		comptes.forEach(c -> {
			if (c.getAbonnement() != null) {
				c.setSolde(c.getSolde() - c.getAbonnement().getFrais());
				if (c.getAbonnement().getCartes() != null) {
					c.getAbonnement().getCartes().forEach(ct -> {
						c.setSolde(c.getSolde() - ct.getFrais());
					});
				}
			}
		});
	}
	
}
