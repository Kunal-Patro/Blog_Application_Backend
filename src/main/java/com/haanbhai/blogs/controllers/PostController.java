package com.haanbhai.blogs.controllers;

import com.haanbhai.blogs.payloads.PostDTO;
import com.haanbhai.blogs.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable Integer userId)
    {
        List<PostDTO> postDTOs = this.postService.getPostsByUser(userId);
        return new ResponseEntity<List<PostDTO>>(postDTOs,HttpStatus.OK);
    }
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable Integer categoryId)
    {
        List<PostDTO> postDTOs = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDTO>>(postDTOs,HttpStatus.OK);
    }
    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId)
    {
        PostDTO foundPostDTO = this.postService.getPostById(postId);
        return new ResponseEntity<PostDTO>(foundPostDTO, HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<PostDTO>> getAllPosts()
    {
        List<PostDTO> postDTOs = this.postService.getAllPosts();
        return new ResponseEntity<List<PostDTO>>(postDTOs, HttpStatus.OK);
    }
}
