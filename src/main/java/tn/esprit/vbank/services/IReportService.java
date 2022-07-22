package tn.esprit.vbank.services;

import tn.esprit.vbank.entities.Report;

public interface IReportService {
	
	long saveReport(Report report);
	
	Report getReport(long id);
}
