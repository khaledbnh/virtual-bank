package tn.esprit.vbank.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.vbank.entities.Employe;
import tn.esprit.vbank.repositories.EmployeRepository;
import tn.esprit.vbank.services.IEmployeService;

@Service
public class EmployeServiceImpl implements IEmployeService{

    @Autowired
    EmployeRepository employeRepository;

	@Override
	public List<Employe> getAllEmployes() {
		List<Employe> employes = null;
		employes = (List<Employe>) employeRepository.findAll();
		
		return (List<Employe>) employeRepository.findAll();
	}
    
	@Override
	public Long addEmploye(Employe employe) {
		
		Employe employeSaved = null; 
		employeSaved = employeRepository.save(employe); 
		return employeSaved.getId(); 
	}
	
	@Override
	public Employe retrieveEmploye(Long id) {
		Employe employe = null; 
			employe =  employeRepository.findById(id).get();
		return employe;
	}
	
	@Override
	public void deleteEmploye(Long id) {
			employeRepository.deleteById(id);
		
	}
}
