package com.haanbhai.blogs.repositiories;

import com.haanbhai.blogs.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
