package tn.esprit.vbank.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.vbank.enums.AppointmentStatus;
import tn.esprit.vbank.enums.UserRole;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employe extends User implements Serializable {

    private static final long serialVersionUID = 1L;
    @JsonIgnore
    @OneToMany(mappedBy = "employe")
    private List<Appointment> appointment;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role = UserRole.EMPLOYE;

    public Employe(Long id, String prenom, String nom, String email, String password, boolean actif, AppointmentStatus appointmentStatus) {
        super();
        this.setId(id);
        this.setFirstName(prenom);
        this.setLastName(nom);
        this.setEmail(email);
        this.setPassword(password);
        this.setActif(actif);
    }


    public Employe(String nom, String prenom, String email, String password, boolean actif, AppointmentStatus appointmentStatus) {
        this.setLastName(nom);
        this.setFirstName(prenom);
        this.setEmail(email);
        this.setPassword(password);
        this.setActif(actif);
    }

    public Employe(String nom, String prenom, String email, boolean actif, AppointmentStatus appointmentStatus) {
        this.setLastName(nom);
        this.setFirstName(prenom);
        this.setEmail(email);
        this.setActif(actif);
    }

    @Override
    public String toString() {
        return "Employe [id=" + getId() + ", firstName=" + getFirstName() + ", lastName=" + getLastName() + ", email=" + getEmail()
                + ", password=" + getPassword() + ", actif=" + isActif() + ", appointment=" + appointment + "]";
    }
}
