package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import com.example.demo.exceptions.DuplicateTrainerException;
import com.example.demo.exceptions.TrainerDeletionException;
import com.example.demo.model.Requirement;
import com.example.demo.model.Trainer;
import com.example.demo.repository.FeedbackRepo;
import com.example.demo.repository.RequirementRepo;
import com.example.demo.repository.TrainerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public class TrainerServiceImpl implements TrainerService {
   private String uploadDir = "src/main/resources/static/images";
   TrainerRepo trainerRepo;
   FeedbackRepo feedbackRepo;
   RequirementRepo requirementRepo;


   @Autowired
   public TrainerServiceImpl(TrainerRepo trainerRepo, FeedbackRepo feedbackRepo, RequirementRepo requirementRepo) {
      this.trainerRepo = trainerRepo;
      this.feedbackRepo = feedbackRepo;
      this.requirementRepo = requirementRepo;
   }
   // public TrainerServiceImpl(TrainerRepo trainerRepo) {
   // this.trainerRepo = trainerRepo;
   // }

   @Override
   public Trainer addTrainerUF(MultipartFile uploadFile, Trainer trainer) {
      if (trainerRepo.existsByEmail(trainer.getEmail())) {
         throw new DuplicateTrainerException("Trainer already exists");
      }
      File directory = new File(uploadDir);
      if (!directory.exists()) {
         directory.mkdir();
      }
      long time = System.currentTimeMillis();
      String fileName = uploadFile.getOriginalFilename();
      String newFileName = fileName.substring(0, fileName.indexOf(".")) + time
            + fileName.substring(fileName.indexOf("."));
      Path filePath = Paths.get(uploadDir, newFileName);
      try {
         Files.write(filePath, uploadFile.getBytes());

      } catch (IOException e) {
         throw new RuntimeException("unable to create file");

      }
      trainer.setResume(newFileName);
      return trainerRepo.save(trainer);
   }

   @Override
   public Trainer addTrainer(Trainer trainer) {
      return trainerRepo.save(trainer);
   }

   @Override
   public boolean deleteTrainer(Long trainerId) {
      Optional<Trainer> t = trainerRepo.findById(trainerId);
      if (t.isEmpty()) {
         throw new TrainerDeletionException("Trainer cannot be deleted");
      }
      if (t.isPresent()) {
         List<Requirement> requirements = requirementRepo.findByTrainer_TrainerId(trainerId);
         for(Requirement r : requirements) {
            r.setTrainer(null);
            requirementRepo.save(r);
         }
         feedbackRepo.deleteByTrainer_TrainerId(trainerId);
         trainerRepo.deleteById(trainerId);
         return true;
      }
      return false;
   }

   @Override
   public List<Trainer> getAllTrainers() {

      return trainerRepo.findAll();
   }

   @Override
   public Optional<Trainer> getTrainerById(Long trainerId) {
      Optional<Trainer> trainer = trainerRepo.findById(trainerId);
      if (trainer.isPresent()) {
         return trainer;
      }
      return Optional.empty();
   }

   @Override
   public Trainer updateTrainer(Long trainerId, Trainer trainer1) {
      Optional<Trainer> optionalTrainer = trainerRepo.findById(trainerId);

      if (optionalTrainer.isPresent()) {
         Trainer trainer = optionalTrainer.get();

         if ("Active".equalsIgnoreCase(trainer.getStatus())) {
            trainer.setStatus("Inactive");
         } else {
            trainer.setStatus("Active");
         }

         return trainerRepo.save(trainer);
      }

      return null;
   }

   @Override
   public Trainer active(Long trainerId, Trainer t1) {
      Optional<Trainer> t = trainerRepo.findById(trainerId);
      if (t.isPresent()) {
         Trainer trainer = t.get();
         if ("Active".equals(trainer.getStatus())) {
            trainer.setStatus("Inactive");
         } else {
            trainer.setStatus("Active");
         }
         return trainerRepo.save(trainer);
      }
      return null;
   }

   @Override
   public Trainer updateTrainerUF(Long id, MultipartFile uploadFile, Trainer trainer) {
      Trainer existingTrainer = trainerRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Trainer not found"));

      // Optional: Check for email conflict if email is being updated
      if (!existingTrainer.getEmail().equals(trainer.getEmail()) &&
            trainerRepo.existsByEmail(trainer.getEmail())) {
         throw new DuplicateTrainerException("Trainer with this email already exists");
      }

      // Handle file upload if a new file is provided
      if (uploadFile != null && !uploadFile.isEmpty()) {
         File directory = new File(uploadDir);
         if (!directory.exists()) {
            directory.mkdir();
         }

         long time = System.currentTimeMillis();
         String fileName = uploadFile.getOriginalFilename();
         String newFileName = fileName.substring(0, fileName.indexOf(".")) + time
               + fileName.substring(fileName.indexOf("."));
         Path filePath = Paths.get(uploadDir, newFileName);

         try {
            Files.write(filePath, uploadFile.getBytes());
         } catch (IOException e) {
            throw new RuntimeException("Unable to create file");
         }

         trainer.setResume(newFileName);
      } else {
         trainer.setResume(existingTrainer.getResume()); // retain old resume
      }

      trainer.setTrainerId(id); // ensure ID is preserved
      return trainerRepo.save(trainer);
   }

}