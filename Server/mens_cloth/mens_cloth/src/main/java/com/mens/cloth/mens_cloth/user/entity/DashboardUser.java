package com.mens.cloth.mens_cloth.user.entity;

import com.mens.cloth.mens_cloth.common.abstractClasses.AuditableEntity;
import com.mens.cloth.mens_cloth.user.enums.InviteStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Table(name = "dashboard_user")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DashboardUser extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "invitation_by")
    private User invitationBy;

    @Column(name = "invitation_status")
    @Enumerated(EnumType.STRING)
    private InviteStatus invitationStatus;

    @Column(name = "invitation_token")
    private String invitationToken;

    @Column(name = "invitation_token_expire")
    private LocalDateTime invitationTokenExpire;


}
