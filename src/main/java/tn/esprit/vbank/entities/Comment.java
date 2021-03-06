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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Comment implements Serializable {

	private static final long serialVersionUID = 6191889143079517027L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Long idComment;

	private String content;

	@Temporal(TemporalType.DATE)
	private Date dateCreation;

	@ManyToOne
	private User user;

	@ManyToOne
	
	private Post post;

	public Set<Comment> getSubComments() {
		return subComments;
	}

	public void setSubComments(Set<Comment> subComments) {
		this.subComments = subComments;
	}

	public Comment getParentComment() {
		return parentComment;
	}

	public void setParentComment(Comment parentComment) {
		this.parentComment = parentComment;
	}

	public Comment() {
		super();
	}

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "parentComment")
	@JsonIgnore
	private Set<Comment> subComments;

	@ManyToOne
	private Comment parentComment; 

	public Long getIdComment() {
		return idComment;
	}

	public void setIdComment(Long idComment) {
		this.idComment = idComment;
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

	public Comment(Long idComment, String content, Date dateCreation, User user, Post post) {
		super();
		this.idComment = idComment;
		this.content = content;
		this.dateCreation = dateCreation;
		this.user = user;
		this.post = post;
	}

}
