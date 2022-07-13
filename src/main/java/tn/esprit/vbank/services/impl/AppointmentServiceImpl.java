package tn.esprit.vbank.services.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.vbank.entities.Appointment;
import tn.esprit.vbank.repositories.AppointmentRepository;
import tn.esprit.vbank.services.IAppointmentService;

@Service
public class AppointmentServiceImpl implements IAppointmentService{

	@Autowired
	AppointmentRepository appointmentRepository;
	
	private static final Logger logger = LogManager.getLogger(AppointmentServiceImpl.class);

	@Override
	public List<Appointment> findAll() {

		List<Appointment> appointments = null;
		try {

			logger.info("In Method retrieveAllContrats :");
			appointments = (List<Appointment>) appointmentRepository.findAll();
			logger.debug("connexion à la DB Ok :");
			for (Appointment appointment : appointments) {
				logger.debug("appointment :" + appointment.getAppointmentStartTime());
			}
			logger.info("Out of Method retrieveAllContrats with Success" + appointments.size());
		} catch (Exception e) {
			logger.error("Out of Method retrieveAllContrats with Errors : " + e);
		}

		return appointments;
	}
	
	@Override
	public Appointment findById(Long id) {
		Appointment appointment = appointmentRepository.findById(id).orElse(null);
        if (appointment == null) {
        	logger.error("No mission Found with ID : " +id);
        }
        return appointment;
	}

	@Override
	public Appointment create(Appointment appointment) {
		Appointment appointmentSaved = null;
		appointmentSaved = appointmentRepository.save(appointment);
		return appointmentSaved;
	}

	@Override
	public Appointment update(Long id, Appointment appointment) {
		Appointment appointmenttUpdated = null;
		appointmenttUpdated = appointmentRepository.save(appointment);
		return appointmenttUpdated;
	}

	@Override
	public void deleteById(Long id) {
		appointmentRepository.deleteById(id); 
		
	}

}
