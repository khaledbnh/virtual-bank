package tn.esprit.vbank.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.vbank.entities.Credit;
import tn.esprit.vbank.services.ICreditService;

@RestController

@RequestMapping("/credit")
public class CreditRestController {
	
	@Autowired
	private ICreditService creditService;
	
	@GetMapping("/retrieve-all-credits")
	public List<Credit> retrieveAllCredits() {
		List<Credit> list = creditService.retrieveAllCredits();
		return list;
	}
	
	@PostMapping("/add-credit")
	public Credit createCredit(@RequestBody Credit c)  {
		Credit credit = creditService.createCredit(c);
		return credit;
	}
	
	@DeleteMapping("/remove-credit/{credit-id}")
	public void deleteCredit(@PathVariable("compte-id") Long  creditId) {
		creditService.deleteCredit(creditId);
	}
	
	

}
