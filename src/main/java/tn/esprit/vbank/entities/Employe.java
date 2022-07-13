package tn.esprit.vbank.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_EMPLOYE")
public class Employe extends User {

    private String role = "employe";

}
