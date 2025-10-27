package com.example.demo.service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.example.demo.exceptions.DuplicateEmailException;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;



@Service
public class OtpService {
    Map<String, String> otpStorage = new HashMap<>();
    private final JavaMailSender mailSender;
    UserRepo userRepo;

    @Autowired
    public OtpService(JavaMailSender mailSender, UserRepo userRepo ) {
        this.mailSender = mailSender;
        this.userRepo = userRepo;
    }

    public Map<String, String> sendOtp(String email) {
        if(userRepo.existsByEmail(email)){
            throw new DuplicateEmailException("Email already exists");
        }
        Map<String, String> otpStorag = new HashMap<>();
        SecureRandom secureRandom = new SecureRandom();
        int otpInt = secureRandom.nextInt(900000) + 100000; // ensures a 6-digit OTP
        String otp = String.valueOf(otpInt);

        otpStorag.put("email", email);
        otpStorag.put("otp", otp);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP is: " + otp);
        mailSender.send(message);

        // Store OTP for verification
        otpStorage.put(email, otp);

        return otpStorag;
    }

    public boolean verifyOtp(String email, String otp) {
        System.out.println(otpStorage);
        return otp.equals(otpStorage.get(email));
    }
}
