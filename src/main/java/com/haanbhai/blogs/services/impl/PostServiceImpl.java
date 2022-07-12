package com.haanbhai.blogs.services.impl;

import com.haanbhai.blogs.entities.Category;
import com.haanbhai.blogs.entities.Post;
import com.haanbhai.blogs.entities.User;
import com.haanbhai.blogs.exceptions.ResourceNotFoundException;
import com.haanbhai.blogs.payloads.PostDTO;
import com.haanbhai.blogs.repositiories.CategoryRepo;
import com.haanbhai.blogs.repositiories.PostRepo;
import com.haanbhai.blogs.repositiories.UserRepo;
import com.haanbhai.blogs.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public Post createPost(PostDTO postDTO, Integer userId, Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category"," category Id",categoryId));
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," user Id ", userId));

        Post post = this.modelMapper.map(postDTO,Post.class);
        post.setImageName("default.png");
        post.setPostCreated(new Date());
        post.setUser(user);
        post.setCategory(cat);
        this.postRepo.save(post);
        return post;
    }

    @Override
    public Post updatePost(PostDTO postDTO, Integer postId) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public Post getPostById(Integer postId) {
        return null;
    }

    @Override
    public List<Post> getPostsByCategory(Integer categoryId) {
        return null;
    }

    @Override
    public List<Post> getPostsByUser(Integer userId) {
        return null;
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        return null;
    }
}
