package com.mens.cloth.mens_cloth.user.controller;

import com.mens.cloth.mens_cloth.common.ApiResponse.APIResponse;
import com.mens.cloth.mens_cloth.user.entity.Customer;
import com.mens.cloth.mens_cloth.user.userService.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/user/dashboard-user")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public APIResponse<?> getAllCustomer(){
        List<Customer> customers = customerService.getAllCustomer();
        return APIResponse.success(customers);
    }
}
