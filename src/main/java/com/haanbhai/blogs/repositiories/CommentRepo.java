package com.haanbhai.blogs.repositiories;

import com.haanbhai.blogs.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
}
