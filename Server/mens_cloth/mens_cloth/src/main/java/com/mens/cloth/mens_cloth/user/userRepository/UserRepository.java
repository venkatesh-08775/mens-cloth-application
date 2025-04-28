package com.mens.cloth.mens_cloth.user.userRepository;

import com.mens.cloth.mens_cloth.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    UserDetails findByEmail(String email);

    Boolean existsByEmail(String email);
}
