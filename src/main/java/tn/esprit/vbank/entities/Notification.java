package tn.esprit.vbank.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.vbank.enums.TypeNotification;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@Table(name = "T_NOTIFICATION")
public class Notification {

	public Notification(String pieceJointe, TypeNotification typeNotification, User user) {
		if (pieceJointe == null) {
			throw new NullPointerException("pieceJointe is marked non-null but is null");
		}
		if (typeNotification == null) {
			throw new NullPointerException("typeNotification is marked non-null but is null");
		}
		if (user == null) {
			throw new NullPointerException("user is marked non-null but is null");
		}
		this.pieceJointe = pieceJointe;
		this.typeNotification = typeNotification;
		this.user = user;
	}

	@Id
	@GeneratedValue
	private Long id;

	private String sujet;

	private String contenu;

	private String pieceJointe;

	private TypeNotification typeNotification;

	@ManyToOne
	private User user;

}
