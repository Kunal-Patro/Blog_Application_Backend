package com.haanbhai.blogs.controllers;

import com.haanbhai.blogs.entities.Comment;
import com.haanbhai.blogs.payloads.ApiResponse;
import com.haanbhai.blogs.payloads.CommentDTO;
import com.haanbhai.blogs.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/user/{userId}/post/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO, @PathVariable Integer postId, @PathVariable Integer userId)
    {
        CommentDTO createdComment = this.commentService.createComment(commentDTO,postId,userId);
        return new ResponseEntity<CommentDTO>(createdComment, HttpStatus.CREATED);
    }
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId)
    {
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment successfully deleted!!!",true), HttpStatus.OK);
    }
}
