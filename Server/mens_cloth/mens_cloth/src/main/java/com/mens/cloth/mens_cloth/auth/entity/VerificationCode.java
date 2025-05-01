package com.mens.cloth.mens_cloth.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mens.cloth.mens_cloth.common.abstractClasses.AuditableEntity;
import com.mens.cloth.mens_cloth.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Data
@Table(name = "verification_code")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VerificationCode  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false)
    private String code;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    @JsonIgnore
    private User user;

    @Column(name = "is_revoked")
    @JsonIgnore
    private Boolean isRevoked;

    @Column(name = "created_at")
    @JsonIgnore
    private LocalDateTime createdAt;

    @Column(name = "expired_at")
    @JsonIgnore
    private LocalDateTime expiredAt;
}
