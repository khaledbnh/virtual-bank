package tn.esprit.vbank.services;


import java.util.List;

import tn.esprit.vbank.entities.Credit;

public interface ICreditService{
	
	List<Credit> retrieveAllCredits();
	public Credit createCredit(Credit c);
	public Credit updateCredit(Credit c,Long id);
	public void deleteCredit(Long id);
	public Credit retrieveCredit(Long id);
	
	
	

	
}
