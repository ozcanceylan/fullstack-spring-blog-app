package com.ozcsoft.springboot.service.impl;

import com.ozcsoft.springboot.dto.CommentDto;
import com.ozcsoft.springboot.entity.Comment;
import com.ozcsoft.springboot.entity.Post;
import com.ozcsoft.springboot.mapper.CommentMapper;
import com.ozcsoft.springboot.repository.CommentRepository;
import com.ozcsoft.springboot.repository.PostRepository;
import com.ozcsoft.springboot.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void createComment(String postUrl, CommentDto commentDto) {
        Post post = postRepository.findByUrl(postUrl).get();
        Comment comment = CommentMapper.mapToComment(commentDto);
        comment.setPost(post);
        commentRepository.save(comment);
    }

    @Override
    public List<CommentDto> findAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(CommentMapper :: mapToCommentDto).collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
