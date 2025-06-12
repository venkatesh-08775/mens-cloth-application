package com.mens.cloth.mens_cloth.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mens.cloth.mens_cloth.auth.enums.UserType;
import com.mens.cloth.mens_cloth.common.abstractClasses.AuditableEntity;
import com.mens.cloth.mens_cloth.user.enums.SignInType;
import com.mens.cloth.mens_cloth.user.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "\"user\"")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends AuditableEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "email",nullable = false,unique = true)
    private String email;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "sign_in_type")
    @Enumerated(EnumType.STRING)
    private SignInType signInType;

    @Column(name = "is_verified")
    @JsonIgnore
    private Boolean isVerified;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserType role;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus status;


    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return getEmail();
    }

}
