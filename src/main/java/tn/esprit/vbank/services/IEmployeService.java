package tn.esprit.vbank.services;

import java.util.List;

import tn.esprit.vbank.entities.Employe;

public interface IEmployeService {

	public List<Employe> getAllEmployes();
	public Long addEmploye(Employe employe);
	public void deleteEmploye(Long id);
	public Employe retrieveEmploye(Long id);
}
