package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;



import jakarta.transaction.Transactional;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback,Long> {
   List<Feedback> findByUser_UserId(Long userId);
   @Modifying
   @Transactional
   void deleteByTrainer_TrainerId(Long trainerId);
   List<Feedback> findByTrainer_TrainerId(Long trainerId);
}

