package tn.esprit.vbank.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.esprit.vbank.enums.AppointmentStatus;

@Entity
@Table(name = "Employe")
public class Employe implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	
	private String lastName;
		 
	private String email;

	private String password;
	
	private boolean actif;
  
  private String role = "employe";
	
	@Enumerated(EnumType.STRING)
	private AppointmentStatus appointmentStatus;
	
	@JsonIgnore
	@OneToMany(mappedBy="employe")
	private List<Appointment> appointment;
	
	public Employe() {
		
	}
	
	public Employe(Long id, String prenom, String nom, String email, String password, boolean actif, AppointmentStatus appointmentStatus) {
		super();
		this.id = id;
		this.firstName = prenom;
		this.lastName = nom;
		this.email = email;
		this.password = password;
		this.actif = actif;
		this.appointmentStatus = appointmentStatus;
	}



	public Employe(String nom, String prenom, String email, String password, boolean actif, AppointmentStatus appointmentStatus) {
		this.lastName = nom;
		this.firstName = prenom;
		this.email = email;
		this.password = password;
		this.actif = actif;
		this.appointmentStatus = appointmentStatus;
	}
	
	public Employe(String nom, String prenom, String email, boolean actif, AppointmentStatus appointmentStatus) {
		this.lastName = nom;
		this.firstName = prenom;
		this.email = email;
		this.actif = actif;
		this.appointmentStatus = appointmentStatus;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public AppointmentStatus getAppointmentStatus() {
		return appointmentStatus;
	}



	public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}



	public boolean isActif() {
		return actif;
	}



	public void setActif(boolean actif) {
		this.actif = actif;
	}



	@Override
	public String toString() {
		return "Employe [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", actif=" + actif + ", appointmentStatus=" + appointmentStatus
				+ ", appointment=" + appointment + "]";
	} 
	
	
}
