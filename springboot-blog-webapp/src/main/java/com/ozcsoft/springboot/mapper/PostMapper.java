package com.ozcsoft.springboot.mapper;

import com.ozcsoft.springboot.dto.PostDto;
import com.ozcsoft.springboot.entity.Post;

import java.util.stream.Collectors;

public class PostMapper {
    // burada olusturulan nesneyi bir diger nesneye donusturecegiz
    // Post to PostDto
    public static PostDto mapToPostDto(Post post){
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .url(post.getUrl())
                .content(post.getContent())
                .shortDescription(post.getShortDescription())
                .createOn(post.getCreateOn())
                .updatedOn(post.getUpdatedOn())
                .comments(post.getComments().stream().map(
                        (comment) -> CommentMapper.mapToCommentDto(comment)
                ).collect(Collectors.toSet()))
                .build();
    }
    // PostDto to Post
    public static Post mapToPost(PostDto postDto){
        return Post.builder()
                .id(postDto.getId())
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .url(postDto.getUrl())
                .shortDescription(postDto.getShortDescription())
                .createOn(postDto.getCreateOn())
                .updatedOn(postDto.getUpdatedOn())
                .build();
    }
}


















