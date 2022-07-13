package tn.esprit.vbank.entities;


import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.vbank.enums.TypeCompte;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_DEMANDE")

public class Demande {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nomClient")
	private String nomClient;
	
	@Column(name="prenomClient")
	private String prenomClient;
	
	@Column(name="tel")
	private String tel;
	
	@Column(name="adresseEmail")
	private String adresseEmail;
	
	@Column(name="dateNaissance")
	private String dateNaissance;
	
	@Column(name="paysNaissance")
	private String paysNaissance;
	
	@Column(name="situationFamiliale")
	private String situationFamiliale;

	
	
	
	
	
	@JsonIgnore
	@OneToMany(mappedBy="demande")
	private List<Compte> comptes;
	
	
	
	
	
	@ManyToOne
    private Client client ;
	
	

}
