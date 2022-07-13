package com.haanbhai.blogs.services;

import com.haanbhai.blogs.entities.Post;
import com.haanbhai.blogs.payloads.PostDTO;
import com.haanbhai.blogs.payloads.PostResponse;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);
    PostDTO updatePost(PostDTO postDTO, Integer postId);
    void deletePost(Integer postId);
    PostDTO getPostById(Integer postId);
    PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
    List<PostDTO> getPostsByCategory(Integer categoryId);
    List<PostDTO> getPostsByUser(Integer userId);

    // Searches all posts by this keyword
    List<PostDTO> searchPosts(String keyword);
}
