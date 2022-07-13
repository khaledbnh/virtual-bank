package tn.esprit.vbank.entities;



import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "T_CLIENT")
public class Client {

	@Column(name="nom")
	private String nom;
	
	@OneToMany(mappedBy="client")
    private List<Demande> demandes ;
}
