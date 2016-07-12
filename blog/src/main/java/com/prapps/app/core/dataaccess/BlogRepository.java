package com.prapps.app.core.dataaccess;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.prapps.app.core.persistence.BlogPostEntity;

public interface BlogRepository extends PagingAndSortingRepository<BlogPostEntity, Long> {
	List<BlogPostEntity> findByStatusOrderByCreatedDesc(String status);
}
