package tn.esprit.vbank.services.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.vbank.entities.Client;
import tn.esprit.vbank.entities.Compte;
import tn.esprit.vbank.entities.Credit;
import tn.esprit.vbank.enums.TypeCompte;
import tn.esprit.vbank.enums.TypeCredit;
import tn.esprit.vbank.repositories.CompteRepository;
import tn.esprit.vbank.repositories.CreditRepository;
import tn.esprit.vbank.services.ICreditService;

@Service
public class CreditServiceImpl implements ICreditService {

	private static final Logger l = LogManager.getLogger(CreditServiceImpl.class);

	@Autowired
	CreditRepository creditRepository;
	@Autowired
	CompteRepository compterepository;

	@Override
	public List<Credit> retrieveAllCredits() {
		return (List<Credit>) creditRepository.findAll();

	}

	@Override
	public Credit createCredit(Credit c) {

		Credit cr = null;

		try {
			l.info("Process started");
			cr = creditRepository.save(c);
			l.info("Saved credit");

		} catch (Exception e) {
			l.error("error in createCredit() : " + e);
		}

		return cr;

	}

	@Override
	public Credit updateCredit(Credit c, Long id) {

		Credit creditUpdated = null;
		try {
			l.info(" Process has started");
			Credit cr = creditRepository.findById(id).get();
			double revenu = c.getRevenu();
			double ms = c.getMontantSouhaite();
			long duree = c.getDureeRemboursement();
			double valProjet = c.getValProjetMax();
			double montantCreditMax = c.getMontantCreditMax();
			double mensualites = c.getMensualites();
			cr.setRevenu(revenu);
			cr.setMontantSouhaite(ms);
			cr.setDureeRemboursement(duree);
			cr.setValProjetMax(valProjet);
			cr.setMontantCreditMax(montantCreditMax);
			cr.setMensualites(mensualites);
			creditUpdated = creditRepository.save(cr);
			l.info("Credit updated");
		} catch (Exception e) {
			l.error("error in updateCredit() : " + e);

		}

		return creditUpdated;
	}

	@Override
	public void deleteCredit(Long id) {

		try {
			l.info("Process started");
			creditRepository.deleteById(id);
			l.info("deleted credit");

		} catch (Exception e) {
			l.error("error in deleteCredit() : " + e);
		}

	}

	@Override
	public Credit retrieveCredit(Long id) {

		Credit crd = null;

		try {
			l.info("Start process");
			crd = creditRepository.findById(id).get();
			l.info("Credit found");

		} catch (Exception e) {
			l.error("error in retrieveCredit() : " + e);
		}

		return crd;
	}

	@Override

	public Credit createCredit(Credit cr, Long idCompte) {

		Compte c = compterepository.findById(idCompte).get();
		cr.setCompte(c);
		if (c.getTypeCompte() == TypeCompte.Courant) {

			if (cr.getTypeCredit() == TypeCredit.Immobilier) {
				double montantCreditMax, mensualites, interet;
				montantCreditMax = (cr.getValProjetMax() * 80) / 100;
				cr.setMontantCreditMax(montantCreditMax);

				if (cr.getMontantSouhaite() <= montantCreditMax) {
					interet = (cr.getMontantSouhaite() * 27.5) / 100;
					mensualites = (cr.getMontantSouhaite() + interet) / cr.getDureeRemboursement();
					cr.setMensualites(mensualites);

				} else {
					l.info("change the montant souhaite");
				}

				return creditRepository.save(cr);
			}

			else if (cr.getTypeCredit() == TypeCredit.Auto) {

				double interet, mensualites;
				interet = (cr.getMontantSouhaite() * 28.1) / 100;
				mensualites = (cr.getMontantSouhaite() + interet) / cr.getDureeRemboursement();
				cr.setMensualites(mensualites);

				return creditRepository.save(cr);

			}
			l.info("problem");
			return null;
		}
		l.info("changer le type du compte  ");
		return null;

	}

	@Override
	public Credit affectercredit(Long idCredit) {
		Credit c = creditRepository.findById(idCredit).get();

		double pm, val;
		pm = c.getRevenu() * 40 / 100;
		val = c.getRevenu() * pm / 100;
		if (c.getMensualites() <= val) {
			int nbrenf;
			double salairesuff, rest;
			rest = c.getRevenu() - c.getMensualites();
			salairesuff = (c.getCompte().getClient().getNbrenf() * 150) + 400;

			if (rest >= salairesuff) {
				c.setApprove(true);
				return creditRepository.save(c);
			}

			else
				c.setApprove(false);
			return creditRepository.save(c);

		}

		return null;
	}

	@Override
	public Credit Createothercredit(Credit cr, Long idCompte) {

		Compte c = compterepository.findById(idCompte).get();
		Credit crs = creditRepository.findById(idCompte).get();

		if (crs.getApprove().equals(true)) {
			double montantpaye, rest, newmontant;
			montantpaye = crs.getMensualites() * cr.getNbrmois();
			rest = crs.getMontantSouhaite() - montantpaye;
			newmontant = cr.getMontantSouhaite() - rest;
			if (newmontant > 0) {
				cr.setApprove(true);
				cr.setCompte(c);
				cr.setMontantSouhaite(newmontant);

				/*
				 * double interet,mensualites; interet = (cr.getMontantSouhaite()*28.1)/100;
				 * mensualites = (cr.getMontantSouhaite()+ interet)/cr.getDureeRemboursement();
				 * cr.setMensualites(mensualites);
				 */
				if (cr.getTypeCredit() == TypeCredit.Immobilier) {
					double montantCreditMax, mensualites, interet;
					montantCreditMax = (cr.getValProjetMax() * 80) / 100;
					cr.setMontantCreditMax(montantCreditMax);

					if (cr.getMontantSouhaite() <= montantCreditMax) {
						interet = (cr.getMontantSouhaite() * 27.5) / 100;
						mensualites = (cr.getMontantSouhaite() + interet) / cr.getDureeRemboursement();
						cr.setMensualites(mensualites);
					} else {
						l.info("change the montant souhaite");
					}

					return creditRepository.save(cr);
				}

				else if (cr.getTypeCredit() == TypeCredit.Auto) {

					double interet, mensualites;
					interet = (cr.getMontantSouhaite() * 28.1) / 100;
					mensualites = (cr.getMontantSouhaite() + interet) / cr.getDureeRemboursement();
					cr.setMensualites(mensualites);

					return creditRepository.save(cr);

				}
			} else
				cr.setApprove(false);
			return creditRepository.save(cr);
		}
		cr.setApprove(false);
		return creditRepository.save(cr);

	}

}
