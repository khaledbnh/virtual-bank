package tn.esprit.vbank.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
@Table(name = "T_COMPTE")

public class Compte implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3477562731811202080L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long compteId;

	@Enumerated(EnumType.STRING)
	@Column(name = "typeCompte")
	private TypeCompte typeCompte;

	@Column(name = "numeroCompte")
	private String numeroCompte;

	@Column(name = "propiétaire")
	private String propiétaire;

	@Column(name = "solde")
	private double solde;

	@Column(name = "dateOuverture")
	private Date dateOuverture;

	@ManyToOne
	@JsonIgnore
	private Demande demande;

	@OneToMany(mappedBy = "compteSource", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Transaction> transactionsSortants;

	@OneToMany(mappedBy = "compteDestinataire", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Transaction> transactionsEntrants;
	
	@JsonIgnore
	@ManyToOne
	private Client client;

	@JsonIgnore
	@OneToMany(mappedBy = "compte")
	private List<Credit> credits;
	
}
