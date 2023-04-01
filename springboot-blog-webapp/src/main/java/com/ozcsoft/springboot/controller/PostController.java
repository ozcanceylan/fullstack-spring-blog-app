package com.ozcsoft.springboot.controller;

import com.ozcsoft.springboot.dto.CommentDto;
import com.ozcsoft.springboot.dto.PostDto;
import com.ozcsoft.springboot.entity.Post;
import com.ozcsoft.springboot.service.CommentService;
import com.ozcsoft.springboot.service.PostService;
import com.ozcsoft.springboot.service.impl.PostServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {
    private PostService postService;
    private CommentService commentService;

    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("/admin/posts")
    public String posts(Model model){
        List<PostDto> posts = postService.findAllPosts();
        model.addAttribute("posts", posts);
        return "/admin/posts";
    }
    @GetMapping("/admin/posts/newpost")
    public String newPost(Model model){
       PostDto postDto = new PostDto();
        model.addAttribute("post", postDto);
        return "/admin/create_post";
    }
    @GetMapping("/admin/comments")
    public String postComments(Model model){
        List<CommentDto> comments = commentService.findAllComments();
        model.addAttribute("comments", comments);
        return "admin/comments";
    }
    @PostMapping("/admin/posts")
    // @ Valid dogrulama yapar PostDto da nasil olacagini yazdik // BindingResult siteden geri donus alir eger herhangi bir hata varsa validation kisminda
    public String createPost(@Valid @ModelAttribute("post") PostDto postDto, BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("post", postDto);
            return "admin/create_post";
        }
        postDto.setUrl(PostServiceImpl.titleToUrl(postDto.getTitle()));
        postService.createPost(postDto);
        //redirect linki GetMappingden aldim
        return "redirect:/admin/posts";
    }
    @GetMapping("/admin/posts/{postId}/edit")
    public String editPostForm(@PathVariable Long postId, Model model){
        PostDto post = postService.findPostById(postId);
        model.addAttribute("post", post);
        return "admin/edit_post";
    }
    @PostMapping("/admin/posts/{postId}")
    public String updatePost(@PathVariable Long postId, @Valid @ModelAttribute("post") PostDto post, BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("post", post);
            return "admin/edit_post";
        }
        post.setId(postId);
        postService.updatePost(post);
        return "redirect:/admin/posts";
    }
    @GetMapping("/admin/posts/{postId}/delete")
    public String deletePost(@PathVariable Long postId){
        postService.deletePostById(postId);
        return "redirect:/admin/posts";
    }
    // pathvariableden sonra eger 1 tane path variable varsa icine onu yazmaya gerek yok yukaridaki gibi
    @GetMapping("/admin/posts/{postUrl}/view")
    public String viewPost(@PathVariable("postUrl") String postUrl, Model model){
        PostDto postdto = postService.findPostByUrl(postUrl);
        model.addAttribute("post", postdto);
        return "admin/view_post";
    }
    // admin/posts/search?query=java  bu sekilde url olacak biz de querynin degerini alip kullanacagiz
    @GetMapping("admin/posts/search")
    public String searchPosts(@RequestParam(value="query") String query, Model model){
        List<PostDto> posts = postService.searchPosts(query);
        model.addAttribute("posts", posts);
        return "admin/posts";
    }
    @GetMapping("/admin/post/comments/{commentId}")
    public String deleteComment(@PathVariable("commentId") Long commentId){
        commentService.deleteComment(commentId);
        return "redirect:/admin/comments";
    }
}
