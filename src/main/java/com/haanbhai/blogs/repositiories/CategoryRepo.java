package com.haanbhai.blogs.repositiories;

import com.haanbhai.blogs.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
