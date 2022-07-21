package tn.esprit.vbank.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends User implements Serializable {

    private static final long serialVersionUID = 3L;

    @Column(name = "role")
    private String role = "admin";

}
