package tn.esprit.vbank.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.vbank.enums.UserRole;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User implements Serializable {

	private static final long serialVersionUID = 4L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "email", unique = true)
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private UserRole role = UserRole.USER;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "password")
	private String password;

	@Column(name = "actif")
	private boolean actif;

	@Column(name = "age")
	private int age;

	@Column(name = "token")
	private String token;

	@Column(columnDefinition = "timestamp")
	private LocalDateTime tokenCreationDate;

	@OneToMany(mappedBy = "user")
	private Set<Notification> notifications = new HashSet();

}
