package com.haanbhai.blogs.services.impl;

import com.haanbhai.blogs.entities.User;
import com.haanbhai.blogs.payloads.UserDTO;
import com.haanbhai.blogs.repositiories.UserRepo;
import com.haanbhai.blogs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = this.dtoToUser(userDTO);
        User savedUser = this.userRepo.save(user);
        return this.userToUserDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO user, Integer usedId) {
        return null;
    }

    @Override
    public UserDTO getUserById(Integer usedId) {
        return null;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return null;
    }

    @Override
    public void deleteUser(Integer userId) {

    }

    private User dtoToUser(UserDTO userDto)
    {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        user.setActive(userDto.isActive());
        return user;
    }
    private UserDTO userToUserDTO(User user)
    {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setAbout(user.getAbout());
        userDTO.setActive(user.isActive());
        return userDTO;
    }
}
