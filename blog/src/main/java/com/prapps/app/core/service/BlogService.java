package com.prapps.app.core.service;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.LockModeType;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import com.prapps.app.core.dataaccess.BlogRepository;
import com.prapps.app.core.dto.BlogPost;
import com.prapps.app.core.exception.BlogServiceException;
import com.prapps.app.core.persistence.BlogPostEntity;
import com.prapps.app.core.util.CollectionUtil;
import com.prapps.app.core.util.time.TimeUtil;

@Service
public class BlogService {

	private BlogRepository blogRepository;
	private TimeUtil timeUtil;
	
	@Inject
	public BlogService(BlogRepository blogRepository, TimeUtil timeUtil) {
		this.blogRepository = blogRepository;
		this.timeUtil = timeUtil;
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
		} catch (InstantiationException | IllegalAccessException e) {
			throw new BlogServiceException(e);
		}
	}
	
	public BlogPost getBlog(Long id) {
		BlogPost blogPost = new BlogPost();
		BeanUtils.copyProperties(blogRepository.findOne(id), blogPost);
		return blogPost;
	}
}
