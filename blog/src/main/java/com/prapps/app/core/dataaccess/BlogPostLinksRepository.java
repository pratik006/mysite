package com.prapps.app.core.dataaccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prapps.app.core.persistence.BlogPostLinkEntity;

public interface BlogPostLinksRepository extends JpaRepository<BlogPostLinkEntity, Long> {
	List<BlogPostLinkEntity> findByBlogId(Long blogId);
}
