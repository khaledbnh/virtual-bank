package tn.esprit.vbank.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class Post implements Serializable {

	private static final long serialVersionUID = 6191889143079517027L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Long idPost;
	
	private String title;

	@Temporal(TemporalType.DATE)
	private Date dateCreation;
	
		
	private String photo;
	
	private String video;
	
	private String body;
	
	@Enumerated(EnumType.STRING)
	private Status status=Status.ACTIVE;
	
	private int nbeComments;
	
	public int getNbeComments() {
		return nbeComments;
	}


	public void setNbeComments(int nbeComments) {
		this.nbeComments = nbeComments;
	}


	public Post(String title, Date dateCreation, String photo, String video, String body, User user,
			Set<Comment> listComment, Set<Like> listLike) {
		super();
		this.title = title;
		this.dateCreation = dateCreation;
		this.photo = photo;
		this.video = video;
		this.body = body;
		this.user = user;
		this.listComment = listComment;
		this.listLike = listLike;
	}


	public Long getIdPost() {
		return idPost;
	}


	public void setIdPost(Long idPost) {
		this.idPost = idPost;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Date getDateCreation() {
		return dateCreation;
	}


	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public String getVideo() {
		return video;
	}


	public void setVideo(String video) {
		this.video = video;
	}


	public String getBody() {
		return body;
	}


	public void setBody(String body) {
		this.body = body;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Set<Comment> getListComment() {
		return listComment;
	}


	public void setListComment(Set<Comment> listComment) {
		this.listComment = listComment;
	}


	public Set<Like> getListLike() {
		return listLike;
	}


	public void setListLike(Set<Like> listLike) {
		this.listLike = listLike;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@ManyToOne()
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="post",fetch=FetchType.EAGER)
	@JsonIgnore
	private Set<Comment> listComment;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="post",fetch=FetchType.EAGER)
	@JsonIgnore
	private Set<Signal> listSignal;
	 
	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public Set<Signal> getListSignal() {
		return listSignal;
	}


	public void setListSignal(Set<Signal> listSignal) {
		this.listSignal = listSignal;
	}


	@OneToMany(cascade = CascadeType.ALL,mappedBy="post",fetch=FetchType.EAGER)
	@JsonIgnore
	private Set<Like> listLike;

	public Post() {
		super();
	}


	
	
}
