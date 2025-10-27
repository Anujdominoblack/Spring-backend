package com.example.demo.controller;

import java.util.List;

import com.example.demo.model.Requirement;
import com.example.demo.repository.RequirementRepo;
import com.example.demo.service.RequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/requirement")
public class RequirementController {
    RequirementService requirementService;
    RequirementRepo requirementRepo;
    

    @Autowired
    public RequirementController(RequirementService requirementService, RequirementRepo requirementRepo) {
        this.requirementService = requirementService;
        this.requirementRepo = requirementRepo;
    }

    
    @GetMapping
    public ResponseEntity<List<Requirement>> getAllRequiremnt() {
        List<Requirement> r = requirementService.getAllRequirements();
        if (r.isEmpty()) {
            return ResponseEntity.status(404).build();

        }
        return ResponseEntity.status(200).body(r);
    }

    @PostMapping
    @PreAuthorize("hasRole('Manager')")
    public ResponseEntity<Requirement> addRequiremnt( @Valid @RequestBody Requirement requirement) {
        Requirement r = requirementService.addRequirement(requirement);
        if (r == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(201).body(r);
    }

    @GetMapping("/{requirementId}")
    @PreAuthorize("hasRole('Manager')")
    public ResponseEntity<Requirement> getRequirementById(@PathVariable Long requirementId) {
        Requirement requirement = requirementService.getRequirementById(requirementId);
        if (requirement == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(requirement);
    }

    @PutMapping("/{requirementId}")
    public ResponseEntity<Requirement> updateRequirement(@PathVariable Long requirementId,
            @RequestBody Requirement requirement) {
        Requirement req = requirementService.updateRequirement(requirementId, requirement);
        if (req == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(201).body(req);
    }

    @PutMapping("/{requirementId}/trainer/{trainerId}")
    public ResponseEntity<Requirement> assignTrainerToRequirement(
            @PathVariable Long requirementId,
            @PathVariable Long trainerId) {
        Requirement updatedRequirement = requirementService.assignRequiremtToTrainer(requirementId, trainerId);
        if (updatedRequirement != null) {
            return ResponseEntity.ok(updatedRequirement);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/{requirementId}")
    public ResponseEntity<?> deleterequirement(@PathVariable Long requirementId){
        boolean b = requirementService.deleteRequirement(requirementId);
        if(!b){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(b);

    }
    @PutMapping("/{id}/status")
    public ResponseEntity<Requirement> updateStatus(@PathVariable Long id,@RequestParam String status){
        Requirement req = requirementService.getRequirementById(id);
        req.setStatus(status);
        Requirement updated  = requirementRepo.save(req);
        return ResponseEntity.status(200).body(updated);


    }
    @PutMapping("/{id}/reject-trainer")
    public ResponseEntity<Requirement> rejectTrainer(@PathVariable Long id){
        Requirement req = requirementService.getRequirementById(id);
        req.setTrainer(null);
        Requirement updated = requirementRepo.save(req);
        return ResponseEntity.status(200).body(updated);

    }

}
