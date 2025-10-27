
package com.example.demo.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class Requirement {
    @Id
    @GeneratedValue
    Long requirementId;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Department is required")
    private String department;


    @NotNull(message = "Posted date is required")
    private LocalDate postedDate;

    @NotBlank(message = "Status is required")
    private String status;

    @NotBlank(message = "Duration is required")
    private String duration;

 
    @NotBlank(message = "Mode is required")
    private String mode;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Skill level is required")
    private String skillLevel;

  
    @NotNull(message = "Budget is required")
    @Positive(message = "Budget must be a positive number")
    private Double budget;

    @NotBlank(message = "Priority is required")
    private String priority;

    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = true)
    Trainer trainer;

    public Requirement() {
    }

    public Requirement(Long requirementId, String title, String description, String department, LocalDate postedDate,
            String status, String duration, String mode, String location, String skillLevel, Double budget,
            String priority, Trainer trainer) {
        this.requirementId = requirementId;
        this.title = title;
        this.description = description;
        this.department = department;
        this.postedDate = postedDate;
        this.status = status;
        this.duration = duration;
        this.mode = mode;
        this.location = location;
        this.skillLevel = skillLevel;
        this.budget = budget;
        this.priority = priority;
        this.trainer = trainer;
    }

    public Long getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(Long requirementId) {
        this.requirementId = requirementId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDate getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(LocalDate postedDate) {
        this.postedDate = postedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

}


