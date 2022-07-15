package com.haanbhai.blogs.repositiories;

import com.haanbhai.blogs.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Integer> {
}
