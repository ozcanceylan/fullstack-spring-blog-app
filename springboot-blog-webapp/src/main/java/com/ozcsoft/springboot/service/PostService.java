package com.ozcsoft.springboot.service;

import com.ozcsoft.springboot.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> findAllPosts();
    void createPost(PostDto postDto);
    PostDto findPostById(Long postid);
    void updatePost(PostDto postDto);
    void deletePostById(Long id);

    PostDto findPostByUrl(String postUrl);
    List<PostDto> searchPosts(String query);
}
