package tn.esprit.vbank.entities;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name = "T_CREDIT")
public class Credit {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long creditId;
	
	private double revenu;
	private double montantSouhaite;
	private String dureeRemboursement;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Compte compte;

}