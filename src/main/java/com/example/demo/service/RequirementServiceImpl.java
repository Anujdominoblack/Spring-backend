package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Requirement;
import com.example.demo.model.Trainer;
import com.example.demo.repository.RequirementRepo;
import com.example.demo.repository.TrainerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RequirementServiceImpl implements RequirementService {
    RequirementRepo requirementRepo;
    TrainerRepo trainerRepo;

    @Autowired
    public RequirementServiceImpl(RequirementRepo requirementRepo, TrainerRepo trainerRepo) {
        this.requirementRepo = requirementRepo;
        this.trainerRepo = trainerRepo;
    }

    @Override
    public Requirement addRequirement(Requirement requirement) {
        return requirementRepo.save(requirement);
    }

    @Override
    public boolean deleteRequirement(Long requirementId) {
        Optional<Requirement> r = requirementRepo.findById(requirementId);
        if (r.isPresent()) {
            requirementRepo.deleteById(requirementId);
            return true;
        }
        return false;
    }

    @Override
    public List<Requirement> getAllRequirements() {
        return requirementRepo.findAll();
    }

    @Override
    public Requirement getRequirementById(Long requirementId) {
        Optional<Requirement> r = requirementRepo.findById(requirementId);
        if (r.isPresent()) {
            Requirement requirement = r.get();
            return requirement;
        }
        return null;
    }

    @Override
    public List<Requirement> getRequirementByTrainerId(Long trainerId) {
        List<Requirement> requirement = requirementRepo.findByTrainer_TrainerId(trainerId);
        if (!requirement.isEmpty()) {
            return requirement;
        }
        return null;
    }

    @Override
    public Requirement updateRequirement(Long requirementId, Requirement requirement) {
        Optional<Requirement> r = requirementRepo.findById(requirementId);
        if (r.isPresent()) {
            Requirement req = r.get();
            req.setBudget(requirement.getBudget());
            req.setDepartment(requirement.getDepartment());
            req.setDescription(requirement.getDescription());
            req.setDuration(requirement.getDuration());
            req.setLocation(requirement.getLocation());
            req.setMode(requirement.getMode());
            req.setPostedDate(requirement.getPostedDate());
            req.setPriority(requirement.getPriority());
            req.setStatus(requirement.getStatus());
            req.setSkillLevel(requirement.getSkillLevel());
            req.setTitle(requirement.getTitle());
            
            return requirementRepo.save(req);

        }
        return null;
    }

    @Override
    public Requirement assignRequiremtToTrainer(Long requirementId, Long trainerId) {
        Optional<Requirement> r = requirementRepo.findById(requirementId);
        Optional<Trainer> t = trainerRepo.findById(trainerId);
        if (r.isEmpty() || t.isEmpty()) {
            return null;
        }
        Requirement requirement = r.get();
        Trainer trainer = t.get();
        requirement.setTrainer(trainer);
        return requirementRepo.save(requirement);

    }

}
