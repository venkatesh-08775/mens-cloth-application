package com.mens.cloth.mens_cloth.user.entity;

import com.mens.cloth.mens_cloth.common.abstractClasses.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "address")
    private String address;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "pin_code")
    private String pin_code;


}
