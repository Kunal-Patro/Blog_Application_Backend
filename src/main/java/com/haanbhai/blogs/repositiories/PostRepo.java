package com.haanbhai.blogs.repositiories;

import com.haanbhai.blogs.entities.Category;
import com.haanbhai.blogs.entities.Post;
import com.haanbhai.blogs.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}
