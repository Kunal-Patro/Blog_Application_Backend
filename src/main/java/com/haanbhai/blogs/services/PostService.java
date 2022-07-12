package com.haanbhai.blogs.services;

import com.haanbhai.blogs.entities.Post;
import com.haanbhai.blogs.payloads.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);
    PostDTO updatePost(PostDTO postDTO, Integer postId);
    void deletePost(Integer postId);
    PostDTO getPostById(Integer postId);
    List<PostDTO> getAllPosts();
    List<PostDTO> getPostsByCategory(Integer categoryId);
    List<PostDTO> getPostsByUser(Integer userId);

    // Searches all posts by this keyword
    List<PostDTO> searchPosts(String keyword);
}
