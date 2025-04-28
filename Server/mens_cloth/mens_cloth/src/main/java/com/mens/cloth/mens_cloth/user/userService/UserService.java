package com.mens.cloth.mens_cloth.user.userService;

import com.mens.cloth.mens_cloth.auth.dto.SignupDto;
import com.mens.cloth.mens_cloth.auth.entity.Token;
import com.mens.cloth.mens_cloth.auth.response.AuthResponse;
import com.mens.cloth.mens_cloth.auth.service.TokenService;
import com.mens.cloth.mens_cloth.user.entity.User;
import com.mens.cloth.mens_cloth.user.enums.SignInType;
import com.mens.cloth.mens_cloth.user.enums.UserStatus;
import com.mens.cloth.mens_cloth.user.userRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


//    @Autowired
//    private PasswordEncoder passwordEncoder;



    public UserDetails findByEmail(String email) {
        if(email == null){
            throw  new RuntimeException("Email is required");
        }
        try{
            return userRepository.findByEmail(email);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean userSignUp(SignupDto payload) throws Exception {
       Boolean isEmailExists =  userRepository.existsByEmail(payload.getEmail());
       if(isEmailExists){
           throw  new Exception("user already exists");
       }
       try{
           User user = User.builder()
                   .name(payload.getName())
                   .email(payload.getEmail())
                   .mobile(payload.getMobile())
                   .password(payload.getPassword())
                   .status(UserStatus.ACTIVE)
                   .isVerified(false)
                   .signInType(SignInType.EMAIL_PASSWORD)
                   .build();
            userRepository.save(user);

            return true;


       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }
}
