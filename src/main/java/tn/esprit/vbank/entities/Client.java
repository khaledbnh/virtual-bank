package tn.esprit.vbank.entities;

import lombok.*;
import tn.esprit.vbank.enums.UserRole;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client extends User implements Serializable {


	@Column(name = "nom")
	private String nom;
	private int nbrenf;
  private static final long serialVersionUID = 2L;
  @OneToMany(mappedBy = "propiétaire")
  private List<Compte> comptes;


 
	@OneToMany(mappedBy = "client")
	private List<Compte> compte;

   @OneToMany(mappedBy = "client")
   private List<Demande> demandes;

   @Enumerated(EnumType.STRING)
   @Column(name = "role")
   private UserRole role = UserRole.CLIENT;

}
