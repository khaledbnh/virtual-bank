package tn.esprit.vbank.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
@Table(name = "T_REPORT")
public class Report implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3043187072451048305L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY,
  	      cascade = {
  	          CascadeType.DETACH,
  	          CascadeType.MERGE
  	      })
  	  @JoinTable(name = "T_COMPTE_REPORTS",
  	        joinColumns = { @JoinColumn(name = "report_id") },
  	        inverseJoinColumns = { @JoinColumn(name = "compte_id") })
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Compte informationsCompte;;
    
    @Column(name = "nomClient")
    private String nomClient;
    
    @ManyToMany(fetch = FetchType.LAZY,
    	      cascade = {
    	          CascadeType.DETACH,
    	          CascadeType.MERGE
    	      })
    	  @JoinTable(name = "T_TRANSACTION_REPORTS", 
    	  joinColumns = { @JoinColumn(name = "report_id") }, 
    	  inverseJoinColumns = { @JoinColumn(name = "transaction_id") })
    private Set<Transaction> transactions = new HashSet<>();

    @Column(name = "totalDepenses")
    private double totalDepenses;
    
    @Column(name = "totalRecu")
    private double totalRecu;
}
