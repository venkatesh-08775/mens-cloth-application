package com.mens.cloth.mens_cloth.user.userService;

import com.mens.cloth.mens_cloth.user.entity.DashboardUser;
import com.mens.cloth.mens_cloth.user.entity.User;
import com.mens.cloth.mens_cloth.user.userRepository.DashboardUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardUserService {


    @Autowired
    private DashboardUserRepository dashboardUserRepository;

    public void createDashboardUser(User user) {
        DashboardUser dashboardUser = DashboardUser.builder()
                .user(user)
                .email(user.getEmail())
                .build();
        dashboardUserRepository.save(dashboardUser);
    }
}
