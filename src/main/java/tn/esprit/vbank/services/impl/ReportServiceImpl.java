package tn.esprit.vbank.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.vbank.entities.Report;
import tn.esprit.vbank.repositories.ReportRepository;
import tn.esprit.vbank.services.IReportService;

@Service
public class ReportServiceImpl implements IReportService {
    
	@Autowired
    ReportRepository reportRepo;

	@Override
	public long saveReport(Report report) {
		return reportRepo.save(report).getId();
	}
	
	@Override
	public Report getReport(long id) {
		Report result = new Report();
		Optional<Report> opt = reportRepo.findById(id);
		if (opt.isPresent()) 
			result = opt.get();
		return result;
	}
}
