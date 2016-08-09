package com.prapps.app.blog.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BlogComment implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long blogId;
	private String comment;
	private String name;
	private String email;
	private Long parentCommentId;
	private List<BlogComment> childComments;
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

	public List<BlogComment> getChildComments() {
		if (childComments == null) {
			 childComments = new ArrayList<BlogComment>(0);
		}
		
		return childComments;
	}

	public void setChildComments(List<BlogComment> childComments) {
		this.childComments = childComments;
	}

	public Calendar getCreated() {
		return created;
	}

	public void setCreated(Calendar created) {
		this.created = created;
	}
}
