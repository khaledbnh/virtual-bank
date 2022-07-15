package tn.esprit.vbank.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.vbank.enums.TypeIdentiteClient;
import tn.esprit.vbank.enums.TypeTransaction;

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
    @Column(name = "id")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "compteId", insertable=false, updatable=false)
    private Compte compteSource;

    @ManyToOne
    @JoinColumn(name = "compteId", insertable=false, updatable=false)
    private Compte compteDestinataire;
    
    private Long compteSourceId;
    private Long compteDestinataireId;
    
    @Column(name = "nomClient")
    private String nomClient;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "typeIdentiteClient")
    private TypeIdentiteClient typeIdentiteClient;
    
    @Column(name = "numeroIdentiteClient")
    private String numeroIdentiteClient;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "typeTransaction")
    private TypeTransaction typeTransaction;
    
    @Column(name = "nomDestinataire")
    private String nomDestinataire;
    
    @Column(name = "montant")
    private double montant;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Column(name = "reference")
    private String reference;

}
