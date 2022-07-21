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
    
    @ManyToOne
    @JoinColumn(name = "compteId", insertable=false, updatable=false)
    private Compte numCompte;;
    
    @Column(name = "nomClient")
    private String nomClient;
    
    @ManyToMany(fetch = FetchType.LAZY,
    	      cascade = {
    	          CascadeType.PERSIST,
    	          CascadeType.MERGE
    	      })
    	  @JoinTable(name = "T_TRANSACTION_REPORTS",
    	        joinColumns = { @JoinColumn(name = "report_id") },
    	        inverseJoinColumns = { @JoinColumn(name = "transaction_id") })
    	  private Set<Transaction> transactions = new HashSet<>();
}
