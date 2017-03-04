package com.prapps.app.blog.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.LockModeType;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prapps.app.blog.dataaccess.BlogCommentRepository;
import com.prapps.app.blog.dataaccess.BlogPostLinksRepository;
import com.prapps.app.blog.dataaccess.BlogRepository;
import com.prapps.app.blog.dto.BlogComment;
import com.prapps.app.blog.dto.BlogPost;
import com.prapps.app.blog.dto.BlogPostLink;
import com.prapps.app.blog.dto.BlogPostStatus;
import com.prapps.app.blog.mapper.BlogMapper;
import com.prapps.app.blog.persistence.BlogCommentEntity;
import com.prapps.app.blog.persistence.BlogPostEntity;
import com.prapps.app.blog.persistence.BlogPostLinkEntity;
import com.prapps.app.core.dto.User;
import com.prapps.app.core.exception.BlogServiceException;
import com.prapps.app.core.util.CollectionUtil;
import com.prapps.app.core.util.PrincipalHelper;
import com.prapps.app.core.util.time.TimeUtil;

@Service @Transactional(readOnly = true)
public class BlogService {

	private BlogRepository blogRepository;
	private BlogCommentRepository blogCommentRepository;
	private BlogPostLinksRepository blogPostLinksRepository;
	private TimeUtil timeUtil;
	private BlogMapper blogMapper;
	private PrincipalHelper principalHelper;
	
	@Inject
	public BlogService(BlogRepository blogRepository, BlogCommentRepository blogCommentRepository, 
			BlogPostLinksRepository blogPostLinksRepository,
			TimeUtil timeUtil, BlogMapper blogMapper, PrincipalHelper principalHelper) {
		this.blogRepository = blogRepository;
		this.blogCommentRepository = blogCommentRepository;
		this.blogPostLinksRepository = blogPostLinksRepository;
		this.timeUtil = timeUtil;
		this.blogMapper = blogMapper;
		this.principalHelper = principalHelper;
	}
	
	public BlogPost create(BlogPost post) {
		User user = principalHelper.getUserDetails();
		BlogPostEntity entity = new BlogPostEntity();
		BeanUtils.copyProperties(post, entity);
		entity.setCreated(timeUtil.getCurrentTime());
		entity.setCreatedBy(user.getFullName());
		entity.setUpdated(timeUtil.getCurrentTime());
		entity.setUpdatedBy(user.getFullName());
		entity = blogRepository.save(entity);
		BeanUtils.copyProperties(entity, post);
		return post;
	}
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public BlogPost update(BlogPost post) {
		User user = principalHelper.getUserDetails();
		BlogPostEntity entity = blogRepository.findOne(post.getId());
		post.setCreated(entity.getCreated());
		post.setCreatedBy(entity.getCreatedBy());
		BeanUtils.copyProperties(post, entity);
		entity.setUpdated(timeUtil.getCurrentTime());
		entity.setUpdatedBy(user.getFullName());
		entity = blogRepository.save(entity);
		BeanUtils.copyProperties(entity, post);
		return post;
	}
	
	public Collection<BlogPost> findAll() throws BlogServiceException {
		String status = BlogPostStatus.COMPLETE.getCode();
		try {
			return CollectionUtil.copyProperties(blogRepository.findByStatusOrderByCreatedDesc(status), BlogPost.class, new String[]{"comment", "comments", "blogPostLinkEntities"});
		} catch (InstantiationException e) {
			throw new BlogServiceException(e);
		} catch (IllegalAccessException e) {
			throw new BlogServiceException(e);
		}
	}
	
	public BlogPost getBlog(Long id) {
		BlogPost blogPost = new BlogPost();
		BlogPostEntity entity = blogRepository.findOne(id);
		BeanUtils.copyProperties(entity, blogPost);
		blogPost.setComments(blogMapper.mapComments(entity.getComments()));
		blogPost.setHasLinks(!entity.getBlogPostLinkEntities().isEmpty());
		return blogPost;
	}
	
	public Collection<BlogComment> create(BlogComment blogComment) {
		BlogCommentEntity newComment = blogMapper.mapComment(blogComment);
		newComment.setCreated(timeUtil.getCurrentTime());
		BlogPostEntity blogEntity = blogRepository.findOne(blogComment.getBlogId());
		if (blogComment.getParentCommentId() == null) {
			blogEntity.getComments().add(newComment);
		} else {
			BlogCommentEntity parent = getParent(blogEntity.getComments(), blogComment.getParentCommentId());
			if (parent != null) {
				parent.getChildComments().add(newComment);
			}
		}
		blogCommentRepository.save(newComment);
		blogRepository.save(blogEntity);
		return blogMapper.mapComments(blogEntity.getComments());
	}
	
	public Collection<BlogComment> getBlogComments(Long blogId) {
		Collection<BlogCommentEntity> blogEntities = blogCommentRepository.findByBlogId(blogId, new Sort(Sort.Direction.DESC, "created"));
		return blogMapper.mapComments(blogEntities);
	}
	
	private BlogCommentEntity getParent(Collection<BlogCommentEntity> comments, Long parentId) {
		for (BlogCommentEntity entity : comments) {
			if (parentId.equals(entity.getId())) {
				return entity;
			}
		}
		return null;
	}
	
	public Collection<BlogPostLink> getBlogPostLinks(Long blogId) {
		Collection<BlogPostLinkEntity> entities = blogPostLinksRepository.findByBlogId(blogId);
		List<BlogPostLink> links = new ArrayList<BlogPostLink>(entities.size());
		for(BlogPostLinkEntity link : entities) {
			links.add(blogMapper.mapBlogPostLink(link));
		}
		return links;
	}
}
