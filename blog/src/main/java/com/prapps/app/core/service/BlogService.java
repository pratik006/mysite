package com.prapps.app.core.service;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.LockModeType;
import javax.persistence.criteria.Order;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import com.prapps.app.core.dataaccess.BlogCommentRepository;
import com.prapps.app.core.dataaccess.BlogRepository;
import com.prapps.app.core.dto.BlogComment;
import com.prapps.app.core.dto.BlogPost;
import com.prapps.app.core.exception.BlogServiceException;
import com.prapps.app.core.mapper.BlogMapper;
import com.prapps.app.core.persistence.BlogCommentEntity;
import com.prapps.app.core.persistence.BlogPostEntity;
import com.prapps.app.core.util.CollectionUtil;
import com.prapps.app.core.util.time.TimeUtil;

@Service
public class BlogService {

	private BlogRepository blogRepository;
	private BlogCommentRepository blogCommentRepository;
	private TimeUtil timeUtil;
	private BlogMapper blogMapper;
	
	@Inject
	public BlogService(BlogRepository blogRepository, BlogCommentRepository blogCommentRepository, TimeUtil timeUtil, BlogMapper blogMapper) {
		this.blogRepository = blogRepository;
		this.blogCommentRepository = blogCommentRepository;
		this.timeUtil = timeUtil;
		this.blogMapper = blogMapper;
	}
	
	public BlogPost create(BlogPost post) {
		BlogPostEntity entity = new BlogPostEntity();
		BeanUtils.copyProperties(post, entity);
		entity.setCreated(timeUtil.getCurrentTime());
		entity.setCreatedBy("Pratik Sengupta");
		entity.setUpdated(timeUtil.getCurrentTime());
		entity.setUpdatedBy("Pratik Sengupta");
		entity = blogRepository.save(entity);
		BeanUtils.copyProperties(entity, post);
		return post;
	}
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public BlogPost update(BlogPost post) {
		BlogPostEntity entity = blogRepository.findOne(post.getId());
		post.setCreated(entity.getCreated());
		post.setCreatedBy(entity.getCreatedBy());
		BeanUtils.copyProperties(post, entity);
		entity.setUpdated(timeUtil.getCurrentTime());
		entity.setUpdatedBy("Pratik Sengupta");
		entity = blogRepository.save(entity);
		BeanUtils.copyProperties(entity, post);
		return post;
	}
	
	public Collection<BlogPost> findAll() throws BlogServiceException {
		try {
			return CollectionUtil.copyProperties(blogRepository.findAll(new Sort(Sort.Direction.DESC, "created")), BlogPost.class);
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
}
