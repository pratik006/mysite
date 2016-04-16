package com.prapps.app.core.dto;

import java.io.Serializable;

public class BlogPostLink implements Serializable {
	private static final long serialVersionUID = -1349982826669712603L;

	private Long id;
	private Long blogId;
	private String url;
	private String comment;
	private BlogPostLinkType blogPostLinkType;
	
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public BlogPostLinkType getBlogPostLinkType() {
		return blogPostLinkType;
	}
	public void setBlogPostLinkType(BlogPostLinkType blogPostLinkType) {
		this.blogPostLinkType = blogPostLinkType;
	}
}
