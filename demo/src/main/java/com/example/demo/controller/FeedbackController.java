package com.example.demo.controller;

import java.util.List;

import com.example.demo.model.Feedback;
import com.example.demo.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    FeedbackService feedbackService;
    @Autowired  
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }
    @PostMapping("/{userId}/{trainerId}")
    @PreAuthorize("hasRole('Manager')")
    ResponseEntity<Feedback> createFeedback(@PathVariable Long userId, @PathVariable Long trainerId, @RequestBody Feedback feedback){
        Feedback fb=feedbackService.createFeedback(userId,trainerId,feedback);
        if(fb!=null){
            return ResponseEntity.status(201).body(fb);
        }
        return ResponseEntity.status(404).build();
    }
    @GetMapping("/{feedbackId}")
    ResponseEntity<Feedback> getFeedbackById(@PathVariable Long feedbackId){
        Feedback fb=feedbackService.getFeedbackById(feedbackId);
        if(fb!=null){
            return ResponseEntity.status(200).body(fb);
        }
        return ResponseEntity.status(404).build();
    }
    @GetMapping
    ResponseEntity<List<Feedback>> getAllFeedbacks(){
        List<Feedback> fbs=feedbackService.getAllFeedbacks();
        if(!fbs.isEmpty()){
            return ResponseEntity.status(200).body(fbs);
        }
        return ResponseEntity.status(404).build();
    }
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('Manager')")
    ResponseEntity<List<Feedback>> getFeedbacksByUserId(@PathVariable Long userId){
        List<Feedback> fbs=feedbackService.getFeedbacksByUserId(userId);
        if(!fbs.isEmpty()){
            System.out.println("Reached cause not empty");
            return ResponseEntity.status(200).body(fbs);
        }
        System.out.println("Reached here");
        return ResponseEntity.status(200).build();
    }
    @DeleteMapping("/{feedbackId}")
    ResponseEntity<?> deleteFeedback(@PathVariable Long feedbackId){
        Feedback fb=feedbackService.deleteFeedback(feedbackId);
        if(fb!=null){
            return ResponseEntity.status(200).body(fb);
        }
        return ResponseEntity.status(404).build();
    }
    @GetMapping("/trainer/{trainerId}")
    @PreAuthorize("hasRole('Manager')")
    ResponseEntity<List<Feedback>> getFeedbacksByTrainerId(@PathVariable Long trainerId){
        List<Feedback> fbs=feedbackService.getFeedbacksByTrainerId(trainerId);
        if(!fbs.isEmpty()){
            System.out.println("Reached cause not empty");
            return ResponseEntity.status(200).body(fbs);
        }
        System.out.println("Reached here");
        return ResponseEntity.status(200).build();
    }

}

