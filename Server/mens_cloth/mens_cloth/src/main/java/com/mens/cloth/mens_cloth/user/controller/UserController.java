package com.mens.cloth.mens_cloth.user.controller;

import com.mens.cloth.mens_cloth.common.ApiResponse.APIResponse;
import com.mens.cloth.mens_cloth.common.util.CurrentUserDetail;
import com.mens.cloth.mens_cloth.user.dto.UserDto;
import com.mens.cloth.mens_cloth.user.entity.User;
import com.mens.cloth.mens_cloth.user.userService.UserService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/health")
    public String checkUser(){
        return CurrentUserDetail.getCurrentUser();
    }

    @GetMapping
    public APIResponse<?> getUser(){
        User user = userService.getUser(CurrentUserDetail.getCurrentUser());
        return APIResponse.success(user);
    }

    @GetMapping("/list")
    public APIResponse<?> fetchUsersList(){
        List<User> users = userService.fetchAllUser();
        return APIResponse.success(users);
    }

    @PutMapping("/{userId}")
    public APIResponse<?> updateUser(@PathVariable Long userId,@RequestBody UserDto payload){
        User updatedUser = userService.updateUser(userId, payload);
        return APIResponse.success(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public  APIResponse<?> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return APIResponse.success();
    }


}
