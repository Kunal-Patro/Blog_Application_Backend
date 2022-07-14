package com.haanbhai.blogs.services;

import com.haanbhai.blogs.payloads.CommentDTO;

public interface CommentService {
    CommentDTO createComment(CommentDTO commentDTO, Integer postId, Integer userId);
    void deleteComment(Integer commentId);
}
