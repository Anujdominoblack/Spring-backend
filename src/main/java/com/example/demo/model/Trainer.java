package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Trainer {
    @Id
    @GeneratedValue
    Long trainerId;

    @NotBlank(message = "Name is required")
    String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phone;

 
    @NotBlank(message = "Expertise is required")
    private String expertise;

    @NotBlank(message = "Experience is required")
    private String experience;

    @NotBlank(message = "Certification is required")
    private String certification;


    @NotBlank(message = "Resume link is required")
    private String resume;

    @NotBlank(message = "Joining date is required")
    private String joiningDate;

    @NotBlank(message = "Status is required")
    private String status;


    public Trainer() {
    }

    public Trainer(Long trainerId, String name, String email, String phone, String expertise, String experience,
            String certification, String resume, String joiningDate, String status) {
        this.trainerId = trainerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.expertise = expertise;
        this.experience = experience;
        this.certification = certification;
        this.resume = resume;
        this.joiningDate = joiningDate;
        this.status = status;
    }

    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getExpertise() {
        return expertise;
    }

    public String getExperience() {
        return experience;
    }

    public String getCertification() {
        return certification;
    }

    public String getResume() {
        return resume;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

}
