package com.prapps.app.blog.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;

public class BlogPost implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String title;
	private String blogCode;
	private String intro;
	private String lead;
	private String coverLink;
	private String content;
	private String status;
	private Collection<BlogComment> comments;
	private Boolean hasLinks;
	private Boolean draft;
	
	private String createdBy;
	private String updatedBy;
	private Calendar created;
	private Calendar updated;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBlogCode() {
		return blogCode;
	}
	public void setBlogCode(String blogCode) {
		this.blogCode = blogCode;
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
	public Collection<BlogComment> getComments() {
		return comments;
	}
	public void setComments(Collection<BlogComment> comments) {
		this.comments = comments;
	}
	public Boolean isDraft() {
		return draft;
	}
	public void setDraft(Boolean draft) {
		this.draft = draft;
	}
	public Boolean getHasLinks() {
		return hasLinks;
	}
	public void setHasLinks(Boolean hasLinks) {
		this.hasLinks = hasLinks;
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
	@Override
	public String toString() {
		return "BlogPost [id=" + id + ", title=" + title + ", intro=" + intro + ", lead=" + lead + ", coverLink="
				+ coverLink + ", content=" + content + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy
				+ ", created=" + created + ", updated=" + updated + "]";
	}
}
