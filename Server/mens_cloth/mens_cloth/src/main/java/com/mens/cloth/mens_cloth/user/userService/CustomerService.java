package com.mens.cloth.mens_cloth.user.userService;

import com.mens.cloth.mens_cloth.user.entity.Customer;
import com.mens.cloth.mens_cloth.user.entity.User;
import com.mens.cloth.mens_cloth.user.userRepository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void createCustomer(User user) {
        Customer customer = Customer.builder()
                .user(user)
                .build();
        customerRepository.save(customer);
    }

    public List<Customer> getAllCustomer() {
        try{
            return customerRepository.findAll();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
