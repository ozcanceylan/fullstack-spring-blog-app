package com.ozcsoft.springboot.controller;

import com.ozcsoft.springboot.dto.CommentDto;
import com.ozcsoft.springboot.dto.PostDto;
import com.ozcsoft.springboot.service.CommentService;
import com.ozcsoft.springboot.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {
    private CommentService commentService;
    private PostService postService;

    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService=postService;
    }

    @PostMapping("/{postUrl}/comments")
    public String createComment(@PathVariable("postUrl") String postUrl,
                                @ModelAttribute("comment") @Valid CommentDto commentDto, BindingResult  result, Model model){
        PostDto postDto = postService.findPostByUrl(postUrl);
        if (result.hasErrors()){
            model.addAttribute("post", postDto);
            model.addAttribute("comment", commentDto);
            return "blog/blog_post";
        }

        commentService.createComment(postUrl,commentDto);
        return "redirect:/post/" + postUrl;
    }
}
