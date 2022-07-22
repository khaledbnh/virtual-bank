package tn.esprit.vbank.controllers;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.val;
import tn.esprit.vbank.entities.Appointment;
import tn.esprit.vbank.entities.Carte;
import tn.esprit.vbank.services.IAppointmentService;

@RestController
public class AppointmentRestController {

	@Autowired
	IAppointmentService iAppointmentService;
	
	@PostMapping(value = "/addAppointment")
	public ResponseEntity createAppointment(@Valid @RequestBody Appointment appointment) {
		Appointment appointmentPostSave = null;
		try {
			appointmentPostSave = iAppointmentService.create(appointment);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(appointmentPostSave);
	}
	
	/*@PostMapping("/addAppointment")
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
	}*/
	
	
	@DeleteMapping(value = "/deleteAppointmentById/{id}")
	public ResponseEntity deleteById(@PathVariable Long id) {
		try {
			iAppointmentService.deleteById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Appointment deleted");
	}
	
    /*@DeleteMapping("/deleteAppointmentById/{id}") 
	@ResponseBody 
	public void deleteById(@PathVariable("id")Long id)
	{
    	iAppointmentService.deleteById(id);
	}*/
    
	@GetMapping(value = "/getAppointmentById/{id}")
	public ResponseEntity findById(@PathVariable Long id) {
		Appointment appointment = null;
		try {
			appointment = iAppointmentService.findById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(appointment);
	}
	
   /* @GetMapping(value = "getAppointmentById/{id}")
    @ResponseBody
	public Appointment findById(@PathVariable("id") Long id) {

		return iAppointmentService.findById(id);
	}*/
    
	/*@GetMapping(value = "/getAllAppointment")
	public ResponseEntity findAll() {
		List<Appointment> appointments = new ArrayList<>();
		try {
			appointments = iAppointmentService.findAll();
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(appointments);
	}*/
	
	
    @GetMapping(value = "/getAllAppointment")
    @ResponseBody
	public List<Appointment> findAll() {
		return iAppointmentService.findAll();
	}
    
	@PutMapping(value = "/updateAppointment")
	public ResponseEntity update(@RequestBody Appointment appointment, @PathVariable("id") Long id) {
		Appointment appointmentPostSave = null;
		try {
			appointmentPostSave = iAppointmentService.update(id, appointment);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(appointmentPostSave);
	}
	
    /*@PutMapping("/updateAppointment") 
	@ResponseBody 
	public void update(@RequestBody tn.esprit.vbank.entities.Appointment appointmentRequest, @PathVariable("id") Long id)
	{
    	iAppointmentService.update(id, appointmentRequest);
	}*/
}
