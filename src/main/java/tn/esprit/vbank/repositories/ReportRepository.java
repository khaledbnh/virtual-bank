package tn.esprit.vbank.repositories;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.vbank.entities.Report;

public interface ReportRepository extends CrudRepository<Report, Long> {
	
}
