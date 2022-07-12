package com.haanbhai.blogs.controllers;

import com.haanbhai.blogs.payloads.PostDTO;
import com.haanbhai.blogs.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable Integer userId, @PathVariable Integer categoryId)
    {
        PostDTO createPost = this.postService.createPost(postDTO,userId,categoryId);
        return new ResponseEntity<PostDTO>(createPost, HttpStatus.CREATED);
    }
}
