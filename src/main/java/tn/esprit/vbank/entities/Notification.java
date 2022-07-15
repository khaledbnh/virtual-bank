package tn.esprit.vbank.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name = "T_NOTIFICATION")
public class Notification {

	@Id
	@GeneratedValue
	private Long id;

	private String sujet;

	private String contenu;
	
	private String type;

	@ManyToOne
	private User user;

}
