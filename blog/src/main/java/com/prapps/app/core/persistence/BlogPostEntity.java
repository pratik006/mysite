package com.prapps.app.core.persistence;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="BLOG_POST")
public class BlogPostEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue//(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Long id;
	
	@Column(name="BLOG_CODE")
	private String blogCode;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="intro")
	private String intro;
	
	@Column(name="lead")
	private String lead;
	
	@Column(name="COVER_LINK")
	private String coverLink;
	
	@Column(name="CONTENT")
	private String content;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="UPDATED_BY")
	private String updatedBy;
	
	@Column(name="CREATED_TS")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar created;
	
	@Column(name="UPDATED_TS")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar updated;
	
	@OneToMany
	@JoinColumn(name = "blog_id", insertable = false, updatable = false)
	private List<BlogCommentEntity> comments;
	
	@OneToMany
	@JoinColumn(name = "blog_id")
	private List<BlogPostLinkEntity> blogPostLinkEntities;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBlogCode() {
		return blogCode;
	}
	public void setBlogCode(String blogCode) {
		this.blogCode = blogCode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getLead() {
		return lead;
	}
	public void setLead(String lead) {
		this.lead = lead;
	}
	public String getCoverLink() {
		return coverLink;
	}
	public void setCoverLink(String coverLink) {
		this.coverLink = coverLink;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<BlogCommentEntity> getComments() {
		return comments;
	}
	public void setComments(List<BlogCommentEntity> comments) {
		this.comments = comments;
	}
	public Calendar getCreated() {
		return created;
	}
	public void setCreated(Calendar created) {
		this.created = created;
	}
	public Calendar getUpdated() {
		return updated;
	}
	public void setUpdated(Calendar updated) {
		this.updated = updated;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public List<BlogPostLinkEntity> getBlogPostLinkEntities() {
		return blogPostLinkEntities;
	}
	public void setBlogPostLinkEntities(List<BlogPostLinkEntity> blogPostLinkEntities) {
		this.blogPostLinkEntities = blogPostLinkEntities;
	}
}
