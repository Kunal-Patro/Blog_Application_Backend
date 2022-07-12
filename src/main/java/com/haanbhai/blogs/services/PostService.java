package com.haanbhai.blogs.services;

import com.haanbhai.blogs.entities.Post;
import com.haanbhai.blogs.payloads.PostDTO;

import java.util.List;

public interface PostService {
    Post createPost(PostDTO postDTO, Integer userId, Integer categoryId);
    Post updatePost(PostDTO postDTO, Integer postId);
    void deletePost(Integer postId);
    Post getPostById(Integer postId);
    List<Post> getPostsByCategory(Integer categoryId);
    List<Post> getPostsByUser(Integer userId);

    // Searches all posts by this keyword
    List<Post> searchPosts(String keyword);
}
