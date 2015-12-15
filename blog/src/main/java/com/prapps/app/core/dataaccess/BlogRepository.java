package com.prapps.app.core.dataaccess;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.prapps.app.core.persistence.BlogPostEntity;

public interface BlogRepository extends PagingAndSortingRepository<BlogPostEntity, Long> {

}
