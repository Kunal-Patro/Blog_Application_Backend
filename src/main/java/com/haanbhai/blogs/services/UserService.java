package com.haanbhai.blogs.services;

import com.haanbhai.blogs.payloads.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO user);
    UserDTO updateUser(UserDTO user, Integer usedId);
    UserDTO getUserById(Integer usedId);
    List<UserDTO> getAllUsers();
    void deleteUser(Integer userId);
}
