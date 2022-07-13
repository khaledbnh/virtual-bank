package tn.esprit.vbank.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.vbank.enums.TypeCarte;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_CARTE")
public class Carte {

	@Id
	@GeneratedValue
	private Long id;

	@Temporal(value = TemporalType.DATE)
	private Date dateExpiration;

	private int ccv;

	private TypeCarte typeCarte;

	@ManyToOne
	private Compte compte;

}