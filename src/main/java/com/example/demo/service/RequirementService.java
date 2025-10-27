package com.example.demo.service;

import com.example.demo.model.Requirement;

import java.util.List;



public interface RequirementService {
  Requirement addRequirement(Requirement requirement);
  Requirement getRequirementById(Long requirementId);
  List<Requirement> getAllRequirements();
  Requirement updateRequirement(Long requirementId,Requirement requirement);
  boolean deleteRequirement(Long requirementId);
  List<Requirement> getRequirementByTrainerId(Long trainerId);
  Requirement assignRequiremtToTrainer(Long requirementId,Long trainerId);

}
