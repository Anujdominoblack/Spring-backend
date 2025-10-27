
package com.example.demo.controller;

import com.example.demo.model.LoginDTO;
import com.example.demo.model.LoginResponse;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserService;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthController {
    UserService userService;
    UserRepo userRepo;

    @Autowired
    public AuthController(UserService userService, UserRepo userRepo) {
        this.userService = userService;
        this.userRepo = userRepo;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> getLogin(@Valid @RequestBody LoginDTO loginDTO) {
        String token = userService.login(loginDTO.getEmail(),loginDTO.getPassword());
        System.out.println(token);
        
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);

        return ResponseEntity.status(200).body(loginResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user) {
      
        User u = userService.register(user);
        if (u == null) {
            System.out.println("Reached Here" +u);
            return ResponseEntity.status(201).build();
        }
        System.out.println("Success" + u);
        return ResponseEntity.status(201).body(u);

    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User u = userService.getUserById(id);
        if (u == null) {
            System.out.println("Reached Here" +u);
            return ResponseEntity.status(201).build();
        }
        System.out.println("Success" + u);
        return ResponseEntity.status(201).body(u);

    }


}
