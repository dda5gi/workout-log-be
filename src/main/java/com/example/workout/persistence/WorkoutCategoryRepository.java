package com.example.workout.persistence;

import com.example.workout.model.WorkoutCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkoutCategoryRepository extends JpaRepository<WorkoutCategoryEntity, Long> {
    List<WorkoutCategoryEntity> findByOriginal(boolean b);
    List<WorkoutCategoryEntity> findByUserId(String userId);
    Optional<WorkoutCategoryEntity> findById(Long id);
}
