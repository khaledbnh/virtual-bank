package tn.esprit.vbank.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_CLIENT")
public class Client extends User {

    @OneToMany
    @Column(name = "comptes")
    private List<Compte> comptes;

    @Column(name = "role")
    private String role = "";
}
