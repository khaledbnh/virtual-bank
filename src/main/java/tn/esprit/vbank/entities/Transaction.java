package tn.esprit.vbank.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
@Table(name = "T_TRANSACTION")
@SequenceGenerator(name = "transaction_seq", sequenceName = "transaction_sequence", initialValue = 1)
public class Transaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3043187072451048305L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
    private long id;

    private long compteSourceId;

    private long CompteDestinataireId;

    private String nomDestinataire;

    private double montant;

    private Date dateDebut;

    private Date dateFin;

    private String reference;

}
