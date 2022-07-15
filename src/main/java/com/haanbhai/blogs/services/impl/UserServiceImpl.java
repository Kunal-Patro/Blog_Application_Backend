package com.haanbhai.blogs.services.impl;

import com.haanbhai.blogs.entities.User;
import com.haanbhai.blogs.exceptions.ResourceNotFoundException;
import com.haanbhai.blogs.payloads.UserDTO;
import com.haanbhai.blogs.repositiories.UserRepo;
import com.haanbhai.blogs.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = this.dtoToUser(userDTO);
        User savedUser = this.userRepo.save(user);
        return this.userToUserDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer usedId) {
        User user = this.userRepo.findById(usedId).orElseThrow(()-> new ResourceNotFoundException("User"," Id ", usedId.toString()));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());
        User updatedUser = this.userRepo.save(user);
        return this.userToUserDTO(updatedUser);
    }

    @Override
    public UserDTO getUserById(Integer usedId) {
        User user = this.userRepo.findById(usedId).orElseThrow(() -> new ResourceNotFoundException("User"," Id ", usedId.toString()));
        return this.userToUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        List<UserDTO> usersDTO = users.stream().map(user -> this.userToUserDTO(user)).collect(Collectors.toList());
        return usersDTO;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", " Id ", userId.toString()));
        this.userRepo.delete(user);
    }

    private User dtoToUser(UserDTO userDto)
    {
        User user = this.modelMapper.map(userDto,User.class);
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
//        user.setActive(userDto.isActive());
        return user;
    }
    private UserDTO userToUserDTO(User user)
    {
        return this.modelMapper.map(user,UserDTO.class);
    }
}
