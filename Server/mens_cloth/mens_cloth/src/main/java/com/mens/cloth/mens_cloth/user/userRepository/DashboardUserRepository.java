package com.mens.cloth.mens_cloth.user.userRepository;

import com.mens.cloth.mens_cloth.user.entity.DashboardUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DashboardUserRepository extends JpaRepository<DashboardUser,Long> {
}
