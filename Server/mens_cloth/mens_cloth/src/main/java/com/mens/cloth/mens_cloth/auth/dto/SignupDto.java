package com.mens.cloth.mens_cloth.auth.dto;

import com.mens.cloth.mens_cloth.auth.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupDto {

    private String name;
    private String email;
    private String password;
    private String mobile;
    private UserType role;
}
