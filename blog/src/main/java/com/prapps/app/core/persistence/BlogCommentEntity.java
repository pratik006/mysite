package com.prapps.app.core.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "BLOG_COMMENT")
public class BlogCommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	@Column(name = "blog_id")
	private Long blogId;
	@Column(name = "comment")
	private String comment;
	@Column(name = "name")
	private String name;
	@Column(name = "email")
	private String email;
	@Column(name = "parent_id", insertable = false, updatable = false)
	private Long parentCommentId;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "parent_id")
	private List<BlogCommentEntity> childComments;
	@Column(name = "created_ts")
	private Calendar created;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getParentCommentId() {
		return parentCommentId;
	}

	public void setParentCommentId(Long parentCommentId) {
		this.parentCommentId = parentCommentId;
	}

	public List<BlogCommentEntity> getChildComments() {
		if (childComments == null) {
			childComments = new ArrayList<BlogCommentEntity>(0);
		}
		
		return childComments;
	}

	public void setChildComments(List<BlogCommentEntity> childComments) {
		this.childComments = childComments;
	}

	public Calendar getCreated() {
		return created;
	}

	public void setCreated(Calendar created) {
		this.created = created;
	}
}
