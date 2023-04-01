package com.ozcsoft.springboot.repository;

import com.ozcsoft.springboot.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
