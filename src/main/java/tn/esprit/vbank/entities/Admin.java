package tn.esprit.vbank.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_ADMIN")
public class Admin extends User {

    @Column(name = "role")
    private String role = "admin";

}
