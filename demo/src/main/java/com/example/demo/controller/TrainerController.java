package com.example.demo.controller;
 
import java.util.List;
import java.util.Optional;

import com.example.demo.model.Trainer;
import com.example.demo.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
 

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;
 
@RestController
@RequestMapping("api/trainer")
public class TrainerController {
    TrainerService trainerService;
 
    @Autowired
    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }
 
    @GetMapping
    public ResponseEntity<List<Trainer>> getAllTrainer() {
        List<Trainer> trainers = trainerService.getAllTrainers();
        if (trainers == null) {
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.status(200).body(trainers);
    }
 
    @PostMapping("/fileUpload")
    @PreAuthorize("hasRole('Coordinator')")
    public ResponseEntity<?> addTrainer(@Valid @RequestParam("file") MultipartFile uploadFile,
            @RequestParam("data") String strTrainer) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Trainer trainer = objectMapper.readValue(strTrainer, Trainer.class);
            Trainer t = trainerService.addTrainerUF(uploadFile, trainer);
            return ResponseEntity.status(201).body(t);
        } catch (Exception e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
 
    }
     @PutMapping("/fileUpload/{trainerId}")
    public ResponseEntity<Trainer> updateTrainer(
            @PathVariable Long trainerId,
            @RequestPart("file") MultipartFile uploadFile,
            @RequestPart("data") String trainerJson) throws JsonProcessingException {
        Trainer trainer = new ObjectMapper().readValue(trainerJson, Trainer.class);
        Trainer updatedTrainer = trainerService.updateTrainerUF(trainerId, uploadFile, trainer);
        return ResponseEntity.ok(updatedTrainer);
    }

    // dummy FOR TESTCASE
    @PostMapping
    @PreAuthorize("hasRole('Coordinator')")
    public ResponseEntity<Trainer> addTrainer(@RequestBody Trainer trainer) {
        Trainer t = trainerService.addTrainer(trainer);
        if (t == null) {
            return ResponseEntity.status(409).build();
        }
        return ResponseEntity.status(201).body(t);
    }
 
    @GetMapping("{trainerId}")
    public ResponseEntity<Trainer> getTrainerById(@PathVariable Long trainerId) {
        Optional<Trainer> trainer = trainerService.getTrainerById(trainerId);
        if (trainer.isPresent()) {
            return ResponseEntity.status(200).body(trainer.get());
        }
        return ResponseEntity.status(400).build();
 
    }
 
    @PutMapping("{trainerId}")
    @PreAuthorize("hasRole('Coordinator')")
    public ResponseEntity<Trainer> updateTrainer(@PathVariable Long trainerId, @RequestBody Trainer trainer) {
        Trainer t = trainerService.updateTrainer(trainerId, trainer);
        if (t != null) {
            return ResponseEntity.ok(t);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{trainerId}")
    public ResponseEntity<?> deleteTrainer(@PathVariable Long trainerId) {
        boolean b = trainerService.deleteTrainer(trainerId);
        if (b) {
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
 
    }
    @PutMapping("/edit/{trainerId}")
    @PreAuthorize("hasRole('Coordinator')")
    public ResponseEntity<Trainer> activeTrainer(@PathVariable Long trainerId,@RequestBody Trainer t1) {
        Trainer t = trainerService.active(trainerId,t1);
        if (t != null) {
            return ResponseEntity.status(200).body(t);
        }
        return ResponseEntity.status(404).build();
    }
 
}