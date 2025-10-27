package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import com.example.demo.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/otp")
public class OtpController {
   
    private OtpService otpService;
 
    @Autowired
    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }
 
 
    @PostMapping("/send")
    public ResponseEntity<Map<String, String>> sendOtp(@RequestBody String email) {
        Map<String, String> response = new HashMap<>();
        response=otpService.sendOtp(email);
        response.put("message", "OTP sent successfully");
        return ResponseEntity.ok(response);
    }
 
 
    @PostMapping("/verify")
    public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> request) {
        boolean isValid = otpService.verifyOtp(request.get("email"), request.get("otp"));
        return isValid ? ResponseEntity.ok("Verified") : ResponseEntity.status(400).body("Invalid OTP");
    }
}
