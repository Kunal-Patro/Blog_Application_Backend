package com.haanbhai.blogs.services.impl;

import com.haanbhai.blogs.entities.Category;
import com.haanbhai.blogs.entities.Post;
import com.haanbhai.blogs.entities.User;
import com.haanbhai.blogs.exceptions.ResourceNotFoundException;
import com.haanbhai.blogs.payloads.PostDTO;
import com.haanbhai.blogs.payloads.PostResponse;
import com.haanbhai.blogs.repositiories.CategoryRepo;
import com.haanbhai.blogs.repositiories.PostRepo;
import com.haanbhai.blogs.repositiories.UserRepo;
import com.haanbhai.blogs.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category"," category Id",categoryId.toString()));
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," user Id ", userId.toString()));

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
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post"," post Id ",postId.toString()));
        post.setPostTitle(postDTO.getPostTitle());
        post.setContent(postDTO.getContent());
        post.setImageName(postDTO.getImageName());
        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost,PostDTO.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post"," post Id ", postId.toString()));
        this.postRepo.delete(post);
    }

    @Override
    public PostDTO getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post"," post Id ",postId.toString()));
        return this.modelMapper.map(post,PostDTO.class);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pg = PageRequest.of(pageNumber,pageSize,sort);
        Page postPage = this.postRepo.findAll(pg);
        List<Post> allPosts = postPage.getContent();
        List<PostDTO> postDTOs = allPosts.stream().map(post -> this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDTOs);
        postResponse.setPageNumber(postPage.getNumber());
        postResponse.setPageSize(postPage.getSize());
        postResponse.setTotalElements(postPage.getTotalElements());
        postResponse.setTotalPages(postPage.getTotalPages());
        postResponse.setIsLastPage(postPage.isLast());
        return postResponse;
    }

    @Override
    public List<PostDTO> getPostsByCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category"," category Id ",categoryId.toString()));
        List<PostDTO> postsDTO = this.postRepo.findByCategory(cat).stream().map(post -> this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
        return postsDTO;
    }

    @Override
    public List<PostDTO> getPostsByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," user Id ",userId.toString()));
        List<PostDTO> postsDTO = this.postRepo.findByUser(user).stream().map(post -> this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
        return postsDTO;
    }

    @Override
    public List<PostDTO> searchPosts(String keyword) {
        List<PostDTO> postDTOs = this.postRepo.findByPostTitleContaining(keyword).stream().map(post->this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
        return postDTOs;
    }
}
