package com.haanbhai.blogs.controllers;

import com.haanbhai.blogs.payloads.ApiResponse;
import com.haanbhai.blogs.payloads.PostDTO;
import com.haanbhai.blogs.payloads.PostResponse;
import com.haanbhai.blogs.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
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
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                    @RequestParam(value = "pageSize", defaultValue = "5", required = false)Integer pageSize)
    {
        PostResponse postResponse = this.postService.getAllPosts(pageNumber,pageSize);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }
    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId)
    {
        this.postService.deletePost(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Post id successfully deleted!!",true),HttpStatus.OK);
    }
    @PutMapping("/{postId}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable Integer postId)
    {
        PostDTO updatedPostDTO = this.postService.updatePost(postDTO,postId);
        return new ResponseEntity<PostDTO>(updatedPostDTO,HttpStatus.OK);
    }
}
