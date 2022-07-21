package tn.esprit.vbank.entities;

import lombok.*;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_CLIENT")
public class Client extends User {

	@OneToMany
	@Column(name = "comptes")
	private List<Compte> comptes;

	@Column(name = "nom")
	private String nom;
	
	private int nbrenf;

	@OneToMany(mappedBy = "client")
	private List<Demande> demandes;

	 @JsonIgnore	
	@OneToMany(mappedBy = "client")
	private List<Compte> compte;

	@Column(name = "role")
	private String role = "";

}
