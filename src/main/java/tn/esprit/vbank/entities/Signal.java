package tn.esprit.vbank.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "T_Signal")
public class Signal implements Serializable {

	private static final long serialVersionUID = 6191889143079517027L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Long idSignal;

	private String content;
	
	private String signalType;

	@Temporal(TemporalType.DATE)
	private Date dateCreation;

	public String getSignalType() {
		return signalType;
	}


	public void setSignalType(String signalType) {
		this.signalType = signalType;
	}

	@ManyToOne
	private User user;

	@ManyToOne
	
	private Post post;

	
	public Signal() {
		super();
	}


	public Long getIdSignal() {
		return idSignal;
	}

	public void setIdSignal(Long idSignal) {
		this.idSignal = idSignal;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Signal(Long idSignal, String content, Date dateCreation, User user, Post post) {
		super();
		this.idSignal = idSignal;
		this.content = content;
		this.dateCreation = dateCreation;
		this.user = user;
		this.post = post;
	}

}
