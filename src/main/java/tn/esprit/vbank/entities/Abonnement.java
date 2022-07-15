package tn.esprit.vbank.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

	private float frais;

	@OneToMany
	private List<Compte> comptes;

	@OneToMany
	private List<Carte> cartes;

}
