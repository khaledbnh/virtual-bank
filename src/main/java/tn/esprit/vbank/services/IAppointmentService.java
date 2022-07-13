package tn.esprit.vbank.services;

import java.time.LocalDate;
import java.util.List;

import tn.esprit.vbank.entities.Appointment;

public interface IAppointmentService {

	public List<Appointment> findAll();
	
	public Appointment findById(Long id);
	
	public Appointment create(Appointment appointment);
	
	public Appointment update(Long id, Appointment appointment);

	public void deleteById(Long id);

	//public Appointment updateStatus(Long appointmentId, Appointment appointment);

    
}
