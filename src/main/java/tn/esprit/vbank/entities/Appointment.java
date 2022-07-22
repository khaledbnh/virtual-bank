package tn.esprit.vbank.entities;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import tn.esprit.vbank.enums.AppointmentStatus;

@Entity
@Table(name = "Appointment")
public class Appointment {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private Timestamp createdAt = new Timestamp(System.currentTimeMillis());
	@NotNull(message = "Le champ appointmentDate est obligatoire")
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate appointmentDate;
	@NotNull(message = "Le champ appointmentStartTime est obligatoire")
	@JsonFormat(pattern="HH:mm:ss")
	private Time appointmentStartTime;
	private Time appointmentEndTime;
	private String nameOfClient;
	private AppointmentStatus status = AppointmentStatus.Booked;
	
	@ManyToOne
    private Employe employe;
	
	public Appointment() {
		
	}
	
	public Appointment(Long id, Timestamp createdAt, LocalDate appointmentDate, Time appointmentStartTime,
			Time appointmentEndTime, String nameOfClient, AppointmentStatus status) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.appointmentDate = appointmentDate;
		this.appointmentStartTime = appointmentStartTime;
		this.appointmentEndTime = appointmentEndTime;
		this.nameOfClient = nameOfClient;
		this.status = status;
		
	}

	public Appointment(Timestamp createdAt, LocalDate appointmentDate, Time appointmentStartTime,
			Time appointmentEndTime, String nameOfClient) {
		super();
		this.createdAt = createdAt;
		this.appointmentDate = appointmentDate;
		this.appointmentStartTime = appointmentStartTime;
		this.appointmentEndTime = appointmentEndTime;
		this.nameOfClient = nameOfClient;
	}

	public Appointment(LocalDate appointmentDate, Time appointmentStartTime, Time appointmentEndTime,
			String nameOfClient) {
		super();
		this.appointmentDate = appointmentDate;
		this.appointmentStartTime = appointmentStartTime;
		this.appointmentEndTime = appointmentEndTime;
		this.nameOfClient = nameOfClient;
	}

	public Appointment(LocalDate appointmentDate, String nameOfClient) {
		super();
		this.appointmentDate = appointmentDate;
		this.nameOfClient = nameOfClient;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Time getAppointmentStartTime() {
		return appointmentStartTime;
	}

	public void setAppointmentStartTime(Time appointmentStartTime) {
		this.appointmentStartTime = appointmentStartTime;
	}

	public Time getAppointmentEndTime() {
		return appointmentEndTime;
	}

	public void setAppointmentEndTime(Time appointmentEndTime) {
		this.appointmentEndTime = appointmentEndTime;
	}
	@NotNull(message = "Le nom est obligatoire")
	@Size(min = 4, max = 20, message = "Le nobmre de caractere doit compris entre 5 et 20 ")
	public String getNameOfClient() {
		return nameOfClient;
	}

	public void setNameOfClient(String nameOfClient) {
		this.nameOfClient = nameOfClient;
	}


	public AppointmentStatus getStatus() {
		return status;
	}

	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}
	
	
	
	
}
