package tn.esprit.vbank.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

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
@Table(name = "T_CARTE")
public class Carte {

	@Id
	@GeneratedValue
	private Long id;

	private Long uuid;
	
	private String dateExpiration;

	private int ccv;

	private String type;
	
	private double frais;

	@ManyToOne
	private Abonnement abonnement;

}
