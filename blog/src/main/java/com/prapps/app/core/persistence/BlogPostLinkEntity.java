package com.prapps.app.core.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "blog_post_links")
public class BlogPostLinkEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	@Column(name = "url")
	private String url;
	@ManyToOne
	@JoinColumn(name = "blog_id")
	private BlogPostEntity blogId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public BlogPostEntity getBlogId() {
		return blogId;
	}
	public void setBlogId(BlogPostEntity blogId) {
		this.blogId = blogId;
	}
}
