package com.ozcsoft.springboot.service.impl;

import com.ozcsoft.springboot.dto.PostDto;
import com.ozcsoft.springboot.entity.Post;
import com.ozcsoft.springboot.mapper.PostMapper;
import com.ozcsoft.springboot.repository.PostRepository;
import com.ozcsoft.springboot.service.PostService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<PostDto> findAllPosts() {
        List<Post> posts = postRepository.findAll();
        // burada lambda da yapilabilinir (post) -> PostMapper.MapToPostDto(post))
        // methodlar static oldugu icin obje olusturmadan kullandik
        return posts.stream().map(PostMapper::mapToPostDto)
                .collect(Collectors.toList());
    }

    @Override
    public void createPost(PostDto postDto) {
        Post post = PostMapper.mapToPost(postDto);
        postRepository.save(post);
    }

    @Override
    public PostDto findPostById(Long postId) {
        Post post = postRepository.findById(postId).get();
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public void updatePost(PostDto postDto) {
        Post post = PostMapper.mapToPost(postDto);
        postRepository.save(post);
    }

    @Override
    public void deletePostById(Long id) {
        Post post = postRepository.findById(id).get();
        postRepository.delete(post);
    }

    @Override
    public PostDto findPostByUrl(String postUrl) {
        Post post = postRepository.findByUrl(postUrl).get();
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public List<PostDto> searchPosts(String query) {
        List<Post> posts = postRepository.searchPosts(query);
         return posts.stream().map(PostMapper::mapToPostDto).collect(Collectors.toList());
    }


    public static String titleToUrl(String postTitle){
        // title dan url uretme
        String title = postTitle.trim().toLowerCase();
        // \\s+ bosluk anlamina gelir boluklari tire yap
        String url = title.replaceAll("\\s+", "-");
        // harf ve rakam disindakileri tire yap
        url = url.replaceAll("[^A-Za-z0-9]", "-");
        return url;
    }
}
