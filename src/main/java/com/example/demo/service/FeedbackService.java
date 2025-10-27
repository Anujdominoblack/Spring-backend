package com.example.demo.service;

import com.example.demo.model.Feedback;

import java.util.List;



public interface FeedbackService {
    Feedback createFeedback(Long userId,Long trainerId,Feedback feedback);

    Feedback getFeedbackById(Long feedbackId);

    List<Feedback> getAllFeedbacks();

    Feedback deleteFeedback(Long feedbackId);

    List<Feedback> getFeedbacksByUserId(Long userId);

    List<Feedback> getFeedbacksByTrainerId(Long trainerId);
}