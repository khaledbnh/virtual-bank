package tn.esprit.vbank.controllers;

import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.vbank.entities.Appointment;
import tn.esprit.vbank.services.IAppointmentService;

@RestController
public class AppointmentRestController {

	@Autowired
	IAppointmentService iAppointmentService;
	
	@PostMapping("/addAppointment")
	@ResponseBody
	public Time createAppointment(@RequestBody tn.esprit.vbank.entities.Appointment appointmentRequest) {
		Appointment appointment = new Appointment();
		appointment.setAppointmentDate(appointmentRequest.getAppointmentDate());
		appointment.setAppointmentStartTime(appointmentRequest.getAppointmentStartTime());
		appointment.setAppointmentEndTime(appointmentRequest.getAppointmentEndTime());
		appointment.setNameOfClient(appointmentRequest.getNameOfClient());
		appointment.setStatus(appointmentRequest.getStatus());

		iAppointmentService.create(appointment);
		return appointment.getAppointmentStartTime();
	}
	
    @DeleteMapping("/deleteAppointmentById/{idAppointment}") 
	@ResponseBody 
	public void deleteById(@PathVariable("id")Long id)
	{
    	iAppointmentService.deleteById(id);
	}
    
    @GetMapping(value = "getAppointmentById/{idAppointment}")
    @ResponseBody
	public Appointment findById(@PathVariable("id") Long id) {

		return iAppointmentService.findById(id);
	}
    
    @GetMapping(value = "/getAllAppointment")
    @ResponseBody
	public List<Appointment> findAll() {
		return iAppointmentService.findAll();
	}
    
    @PostMapping("/updateAppointment") 
	@ResponseBody 
	public void update(@RequestBody tn.esprit.vbank.entities.Appointment appointmentRequest, @PathVariable("id") Long id)
	{
    	iAppointmentService.update(id, appointmentRequest);
	}
}
