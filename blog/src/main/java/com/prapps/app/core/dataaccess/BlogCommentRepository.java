package com.prapps.app.core.dataaccess;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.prapps.app.core.persistence.BlogCommentEntity;

public interface BlogCommentRepository extends JpaRepository<BlogCommentEntity, Long> {
	List<BlogCommentEntity> findByBlogId(Long blogId, Sort sort);
}
