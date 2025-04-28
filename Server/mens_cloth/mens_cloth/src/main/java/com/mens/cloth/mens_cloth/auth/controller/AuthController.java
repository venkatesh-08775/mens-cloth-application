package com.mens.cloth.mens_cloth.auth.controller;

import com.mens.cloth.mens_cloth.auth.dto.SignupDto;
import com.mens.cloth.mens_cloth.auth.response.AuthResponse;
import com.mens.cloth.mens_cloth.common.ApiResponse.APIResponse;
import com.mens.cloth.mens_cloth.user.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;


    @PostMapping("/signup/email")
    public APIResponse<?> userSignUp(@RequestBody SignupDto payload) throws Exception {
        Boolean signupResponse = userService.userSignUp(payload);
        return APIResponse.success(signupResponse);
    }
}
