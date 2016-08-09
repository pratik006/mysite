package com.prapps.app.blog.dataaccess;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.prapps.app.blog.persistence.BlogCommentEntity;

public interface BlogCommentRepository extends JpaRepository<BlogCommentEntity, Long> {
	List<BlogCommentEntity> findByBlogId(Long blogId, Sort sort);
}
