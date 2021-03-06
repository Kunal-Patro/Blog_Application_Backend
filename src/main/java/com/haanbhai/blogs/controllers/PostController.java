package com.haanbhai.blogs.controllers;

import com.haanbhai.blogs.config.AppConstants;
import com.haanbhai.blogs.payloads.ApiResponse;
import com.haanbhai.blogs.payloads.PostDTO;
import com.haanbhai.blogs.payloads.PostResponse;
import com.haanbhai.blogs.services.FileService;
import com.haanbhai.blogs.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private String path;

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
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                    @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false)Integer pageSize,
                                                    @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false)String sortBy,
                                                    @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false)String sortDir)
    {
        PostResponse postResponse = this.postService.getAllPosts(pageNumber,pageSize,sortBy,sortDir);
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
    @GetMapping("/search")
    public ResponseEntity<List<PostDTO>> searchPosts(@RequestParam(value = "keywords")String keywords)
    {
        List<PostDTO> searchedPosts = this.postService.searchPosts(keywords);
        return new ResponseEntity<List<PostDTO>>(searchedPosts,HttpStatus.OK);
    }
    @PostMapping("/image/upload/{postId}")
    public ResponseEntity<PostDTO> uploadImage(@RequestParam("image")MultipartFile file, @PathVariable Integer postId) throws IOException {
        PostDTO postDTO = this.postService.getPostById(postId);
        String fileName = this.fileService.uploadImage(path,file);
        postDTO.setImageName(fileName);
        PostDTO updatedPost = this.postService.updatePost(postDTO,postId);
        return new ResponseEntity<PostDTO>(updatedPost,HttpStatus.OK);
    }

    @GetMapping(value = "/image/{postId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImage(@PathVariable Integer postId, HttpServletResponse response) throws IOException {
        PostDTO postDTO = this.postService.getPostById(postId);
        InputStream resource = this.fileService.getResource(path,postDTO.getImageName());
        StreamUtils.copy(resource,response.getOutputStream());
    }
}
