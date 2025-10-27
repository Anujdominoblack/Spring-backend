package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.demo.config.JwtUtils;
import com.example.demo.exceptions.DuplicateEmailException;
import com.example.demo.exceptions.DuplicateUserException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

  UserRepo userRepo;
  PasswordEncoder passwordEncoder;
  AuthenticationManager authenticationManager;
  JwtUtils jwtService;

  @Autowired
  public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder,
      AuthenticationManager authenticationManager, JwtUtils jwtService) {
    this.userRepo = userRepo;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
    this.jwtService = jwtService;
  }

  @Override
  public User register(User user) {
    User exitUser = userRepo.findByUsername(user.getUsername());
    User emailUser = userRepo.findByEmail(user.getEmail());
    if(emailUser!=null){
      throw new DuplicateEmailException("Email Already Registered");
    }
    if(exitUser!=null){
      throw new DuplicateUserException("Username already exists");
    }
    if (exitUser == null) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      return userRepo.save(user);
    }
    return null;
  }

  @Override
  public List<User> getAllUser() {
    return userRepo.findAll();
  }

  @Override
  public String login(String email, String password) {
    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(email, password));
    if (authentication.isAuthenticated()) {
      User user = userRepo.findByEmail(email);
      Map<String, Object> claims = new HashMap<>();
      claims.put("userId",user.getUserId());
      claims.put("role",user.getUserRole());
      claims.put("username",user.getUsername());
      return jwtService.generateToken(email, claims);
    }
    return null;
  }

  @Override
  public User getUserById(Long userId) {
   Optional<User> u = userRepo.findById(userId);
   if(u.isPresent()){
    return u.get();
   }
    return null;
  }


}
