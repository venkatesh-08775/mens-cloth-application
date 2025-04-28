package com.mens.cloth.mens_cloth.auth.service;

import com.mens.cloth.mens_cloth.auth.entity.Token;
import com.mens.cloth.mens_cloth.auth.enums.TokenType;
import com.mens.cloth.mens_cloth.auth.repository.TokenRepository;
import com.mens.cloth.mens_cloth.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private JwtService jwtService;

    private String generateAccessToken(User user){
        return jwtService.accessToken(user);
    }

    private String generateRefreshToken(User user){
        return jwtService.refreshToken(user);
    }


    public Token createAccessToken(User user) {
        Token token = Token.builder()
                .token(generateAccessToken(user))
                .tokenType(TokenType.ACCESS)
                .user(user)
                .isRevoked(false)
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusHours(1))
                .build();

        return tokenRepository.save(token);

    }

    public Token createRefreshToken(User user) {
        Token token = Token.builder()
                .token(generateRefreshToken(user))
                .tokenType(TokenType.REFRESH)
                .user(user)
                .isRevoked(false)
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusDays(7))
                .build();
        return tokenRepository.save(token);

    }
}
