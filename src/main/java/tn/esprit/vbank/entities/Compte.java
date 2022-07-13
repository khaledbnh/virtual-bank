package tn.esprit.vbank.entities;


import java.sql.Date;
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
@Table(name = "T_COMPTE")

public class Compte {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long compteId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="typeCompte")
	private TypeCompte typeCompte;
	
	@Column(name="numeroCompte")
	private String numeroCompte;
	
	@Column(name="propiétaire")
	private String propiétaire;
	
	
	@Column(name="solde")
	private String solde;
	
	@Column(name="dateOuverture")
	private Date dateOuverture;
	
	
	@ManyToOne
    private Demande demande ;
	
}