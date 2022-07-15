package tn.esprit.vbank.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client extends User implements Serializable {

    private static final long serialVersionUID = 2L;

    @OneToMany(mappedBy = "propiétaire")
    private List<Compte> comptes;

    @OneToMany(mappedBy = "client")
    private List<Demande> demandes;

    @Column(name = "role")
    private String role = "client";

}
