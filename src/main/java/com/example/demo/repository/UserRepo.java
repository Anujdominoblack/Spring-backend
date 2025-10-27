package com.example.demo.repository;


import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface UserRepo  extends  JpaRepository<User,Long>{
    boolean existsByEmail(String email);
 
    User findByEmail(String email);
 
    User findByUsername(String username);
}
