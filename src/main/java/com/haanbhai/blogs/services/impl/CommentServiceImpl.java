package com.haanbhai.blogs.services.impl;

import com.haanbhai.blogs.entities.Comment;
import com.haanbhai.blogs.entities.Post;
import com.haanbhai.blogs.entities.User;
import com.haanbhai.blogs.exceptions.ResourceNotFoundException;
import com.haanbhai.blogs.payloads.CommentDTO;
import com.haanbhai.blogs.repositiories.CommentRepo;
import com.haanbhai.blogs.repositiories.PostRepo;
import com.haanbhai.blogs.repositiories.UserRepo;
import com.haanbhai.blogs.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDTO createComment(CommentDTO commentDTO, Integer postId, Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," user Id ", userId));
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post"," post Id ", postId));
        Comment comment = this.modelMapper.map(commentDTO,Comment.class);
        comment.setPost(post);
        comment.setUser(user);
        Comment createdComment = this.commentRepo.save(comment);
        return this.modelMapper.map(createdComment,CommentDTO.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment com = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment"," comment Id ", commentId));
        this.commentRepo.delete(com);

    }
}
