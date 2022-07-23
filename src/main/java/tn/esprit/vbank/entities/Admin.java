package tn.esprit.vbank.entities;

import lombok.*;
import tn.esprit.vbank.enums.UserRole;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends User implements Serializable {

    private static final long serialVersionUID = 3L;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role = UserRole.ADMIN;

}
