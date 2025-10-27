package com.example.demo.repository;

import java.util.List;


import com.example.demo.model.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RequirementRepo extends JpaRepository<Requirement,Long> {
List<Requirement> findByTrainer_TrainerId(Long trainerId);
void deleteByTrainer_TrainerId(Long trainerId);
}
