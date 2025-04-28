package com.mens.cloth.mens_cloth.auth.response;

import com.mens.cloth.mens_cloth.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {

    private String accessToken;
    private String refreshToken;
    private User user;
}
