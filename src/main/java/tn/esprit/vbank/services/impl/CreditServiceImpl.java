package tn.esprit.vbank.services.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.vbank.entities.Credit;
import tn.esprit.vbank.repositories.CreditRepository;
import tn.esprit.vbank.services.ICreditService;

@Service
public class CreditServiceImpl implements ICreditService{
	
	private static final Logger l = LogManager.getLogger(CreditServiceImpl.class);
	
	@Autowired
	CreditRepository creditRepository;
	
	@Override
	public List<Credit> retrieveAllCredits() {
	return	(List<Credit>) creditRepository.findAll();
		
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
			Credit cr =creditRepository.findById(id).get();
			creditUpdated = creditRepository.save(cr);
			l.info("Credit updated");
		}
		catch(Exception e){
			l.error("error in updateCredit() : " + e);
			
		}
		return creditUpdated ;
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

	
	
	

}
