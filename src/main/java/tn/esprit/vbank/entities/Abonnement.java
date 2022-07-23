package tn.esprit.vbank.entities;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_ABONNEMENT")
public class Abonnement {

	@Id
	@GeneratedValue
	private Long id;

	private String nom;

	private String description;

	@ElementCollection
	private Map<String, String> avantages = new HashMap<>();

	private String type;

	private double frais;

	@OneToMany(mappedBy = "abonnement", orphanRemoval = true, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Compte> comptes = new HashSet();

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
	private Set<Carte> cartes = new HashSet();

}
