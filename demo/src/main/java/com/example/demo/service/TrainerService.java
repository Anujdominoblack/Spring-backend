package com.example.demo.service;
 
import java.util.List;
import java.util.Optional;

import com.example.demo.model.Trainer;
import org.springframework.web.multipart.MultipartFile;
 

 
public interface TrainerService {
   Trainer addTrainerUF(MultipartFile uploadFile, Trainer trainer);
   Trainer addTrainer(Trainer trainer);
 
   Optional<Trainer> getTrainerById(Long trainerId);
 
   List<Trainer> getAllTrainers();
 
   Trainer updateTrainer(Long trainerId, Trainer trainer);
   Trainer active(Long trainerId,Trainer trainer);
   Trainer updateTrainerUF(Long id, MultipartFile uploadFile, Trainer trainer);
 
   boolean deleteTrainer(Long trainerId);
}