package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;



public interface UserService {
    User register(User user);

    String login(String email, String password);

    List<User> getAllUser();
    User getUserById(Long userId);
} 