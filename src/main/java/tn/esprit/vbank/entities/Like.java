package tn.esprit.vbank.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "T_Like")
public class Like implements Serializable {

	private static final long serialVersionUID = 6191889143079517027L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Long idLike;
	
	private String content;

	@Temporal(TemporalType.DATE)
	private Date dateCreation;
	
	@Enumerated(EnumType.STRING)
	private TLike TLike;
	
	@ManyToOne
	private User user;
	
	
	@ManyToOne
	private Post post;


	public Like(Long idLike, String content, Date dateCreation, tn.esprit.vbank.entities.TLike tLike, User user,
			Post post) {
		super();
		this.idLike = idLike;
		this.content = content;
		this.dateCreation = dateCreation;
		TLike = tLike;
		this.user = user;
		this.post = post;
	}


	public Long getidLike() {
		return idLike;
	}


	public Like() {
		super();
	}


	public void setidLike(Long idLike) {
		this.idLike = idLike;
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


	public TLike getTLike() {
		return TLike;
	}


	public void setTLike(TLike tLike) {
		TLike = tLike;
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
	
	



	
	
}
