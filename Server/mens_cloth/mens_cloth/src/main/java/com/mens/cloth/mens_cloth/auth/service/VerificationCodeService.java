package com.mens.cloth.mens_cloth.auth.service;

import com.mens.cloth.mens_cloth.auth.entity.VerificationCode;
import com.mens.cloth.mens_cloth.auth.repository.VerificationCodeRepository;
import com.mens.cloth.mens_cloth.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class VerificationCodeService {

    @Autowired
    private VerificationCodeRepository verificationCodeRepository;

    private String generateCode(){
        return String.valueOf((int) (Math.random() * 900000) + 100000);
    }

    public VerificationCode createOtp(Long userId){
        User user = User.builder()
                .id(userId)
                .build();

        VerificationCode verificationCode = VerificationCode.builder()
                .code(generateCode())
                .user(user)
                .isRevoked(false)
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusMinutes(5))
                .build();

        return verificationCodeRepository.save(verificationCode);

    }
}
