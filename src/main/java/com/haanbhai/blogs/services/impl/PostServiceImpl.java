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
import java.util.stream.Collectors;

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
    public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category"," category Id",categoryId));
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," user Id ", userId));

        Post post = this.modelMapper.map(postDTO,Post.class);
        post.setImageName("default.png");
        post.setPostCreated(new Date());
        post.setUser(user);
        post.setCategory(cat);
        Post createdPost = this.postRepo.save(post);
        return this.modelMapper.map(createdPost,PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Integer postId) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public PostDTO getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post"," post Id ",postId));
        return this.modelMapper.map(post,PostDTO.class);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<PostDTO> postDTOs = this.postRepo.findAll().stream().map(post -> this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
        return postDTOs;
    }

    @Override
    public List<PostDTO> getPostsByCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category"," category Id ",categoryId));
        List<PostDTO> postsDTO = this.postRepo.findByCategory(cat).stream().map(post -> this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
        return postsDTO;
    }

    @Override
    public List<PostDTO> getPostsByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," user Id ",userId));
        List<PostDTO> postsDTO = this.postRepo.findByUser(user).stream().map(post -> this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
        return postsDTO;
    }

    @Override
    public List<PostDTO> searchPosts(String keyword) {
        return null;
    }
}
