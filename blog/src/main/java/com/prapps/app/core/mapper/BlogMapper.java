package com.prapps.app.core.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.prapps.app.core.dto.BlogComment;
import com.prapps.app.core.dto.BlogPostLink;
import com.prapps.app.core.dto.BlogPostLinkType;
import com.prapps.app.core.persistence.BlogCommentEntity;
import com.prapps.app.core.persistence.BlogPostLinkEntity;

@Component
public class BlogMapper {
	public BlogComment mapComment(BlogCommentEntity entity) {
		BlogComment blogComment = new BlogComment();
		blogComment.setId(entity.getId());
		blogComment.setBlogId(entity.getBlogId());
		blogComment.setComment(entity.getComment());
		blogComment.setCreated(entity.getCreated());
		blogComment.setParentCommentId(entity.getParentCommentId());
		blogComment.setName(entity.getName());
		blogComment.setEmail(entity.getEmail());
		List<BlogComment> children = new ArrayList<BlogComment>(entity.getChildComments().size());
		for (BlogCommentEntity child : entity.getChildComments()) {
			children.add(mapComment(child));
		}
		blogComment.setChildComments(children);
		return blogComment;
	}
	
	public Collection<BlogComment> mapComments(Collection<BlogCommentEntity> entities) {
		Collection<BlogComment> blogPosts = new ArrayList<BlogComment>(entities.size());
		for (BlogCommentEntity entity : entities) {
			blogPosts.add(mapComment(entity));
		}
		
		return blogPosts;
	}
	
	public BlogCommentEntity mapComment(BlogComment dto) {
		BlogCommentEntity blogComment = new BlogCommentEntity();
		blogComment.setId(dto.getId());
		blogComment.setBlogId(dto.getBlogId());
		blogComment.setComment(dto.getComment());
		blogComment.setCreated(dto.getCreated());
		blogComment.setParentCommentId(dto.getParentCommentId());
		blogComment.setName(dto.getName());
		blogComment.setEmail(dto.getEmail());
		List<BlogCommentEntity> children = new ArrayList<BlogCommentEntity>(dto.getChildComments().size());
		for (BlogComment child : dto.getChildComments()) {
			children.add(mapComment(child));
		}
		blogComment.setChildComments(children);
		return blogComment;
	}
	
	public BlogPostLink mapBlogPostLink(BlogPostLinkEntity entity) {
		BlogPostLink link = new BlogPostLink();
		link.setId(entity.getId());
		link.setId(entity.getUrl());
		link.setBlogPostLinkType(BlogPostLinkType.getByType("pic"));
		return link;
	}
	
	public BlogPostLinkEntity mapBlogPostLink(BlogPostLink dto) {
		BlogPostLinkEntity entity = new BlogPostLinkEntity();
		entity.setId(dto.getId());
		entity.setUrl(dto.getUrl());
		return entity;
	}
}
