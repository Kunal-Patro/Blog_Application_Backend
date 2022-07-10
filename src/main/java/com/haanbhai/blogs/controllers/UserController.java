package com.haanbhai.blogs.controllers;

import com.haanbhai.blogs.payloads.ApiResponse;
import com.haanbhai.blogs.payloads.UserDTO;
import com.haanbhai.blogs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO)
    {
        UserDTO createdUserDto = this.userService.createUser(userDTO);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable Integer userId)
    {
        UserDTO updatedUser = this.userService.updateUser(userDTO,userId);
        return ResponseEntity.ok(updatedUser);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId)
    {
        this.userService.deleteUser(userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully", true), HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers()
    {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getSingleUser(@PathVariable Integer userId)
    {
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }
}
