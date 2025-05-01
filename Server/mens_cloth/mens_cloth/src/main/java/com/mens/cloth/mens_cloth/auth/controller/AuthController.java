package com.mens.cloth.mens_cloth.auth.controller;

import com.mens.cloth.mens_cloth.auth.dto.LoginDto;
import com.mens.cloth.mens_cloth.auth.dto.OtpDto;
import com.mens.cloth.mens_cloth.auth.dto.SignupDto;
import com.mens.cloth.mens_cloth.auth.entity.VerificationCode;
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
       VerificationCode verificationCode =  userService.userSignUp(payload);
        return APIResponse.success(verificationCode);
    }

    @PostMapping("/signup/email/verify-otp")
    public APIResponse<?> verifyOtp(@RequestBody OtpDto payload){
        userService.verifyOtp(payload);
        return APIResponse.success();
    }

    @PostMapping("/login/email")
    public APIResponse<?> login(@RequestBody LoginDto payload){
        AuthResponse authResponse = userService.login(payload);
        return APIResponse.success(authResponse);
    }
}
