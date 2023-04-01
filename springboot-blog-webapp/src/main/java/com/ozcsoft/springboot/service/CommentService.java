package com.ozcsoft.springboot.service;

import com.ozcsoft.springboot.dto.CommentDto;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CommentService {
    void createComment(String postUrl, CommentDto commentDto);

    List<CommentDto> findAllComments();

    void deleteComment(Long commentId);
}
