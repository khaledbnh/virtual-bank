package tn.esprit.vbank.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.vbank.entities.Employe;
import tn.esprit.vbank.services.IEmployeService;

@RestController
public class EmployeRestController {

	@Autowired
	IEmployeService iEmployeService;
	
	@PostMapping("/addEmploye")
	@ResponseBody
	public String addEmploye(@RequestBody tn.esprit.vbank.entities.Employe employeRequest) {
		Employe employe = new Employe();
		employe.setFirstName(employeRequest.getFirstName());
		employe.setLastName(employeRequest.getLastName());
		employe.setEmail(employeRequest.getEmail());
		employe.setPassword(employeRequest.getPassword());
		//employe.setActif(employeRequest.get());

		iEmployeService.addEmploye(employeRequest);
		return employe.getFirstName();
	}
	
    @GetMapping(value = "/getAllEmploye")
    @ResponseBody
	public List<Employe> getAllEmployes() {
		return iEmployeService.getAllEmployes();
	}
    
    @DeleteMapping("/deleteEmploye") 
	@ResponseBody 
	public void deleteEmploye(@RequestBody tn.esprit.vbank.entities.Employe employeRequest, @PathVariable("id")Long id)
	{
    	iEmployeService.deleteEmploye(id);
	}
    
    @GetMapping(value = "getEmployeById/{id}")
    @ResponseBody
	public Employe retrieveEmploye(@PathVariable("id") Long id) {

		return iEmployeService.retrieveEmploye(id);
	}
}
