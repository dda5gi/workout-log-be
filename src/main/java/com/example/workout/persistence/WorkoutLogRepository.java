package com.example.workout.persistence;

import com.example.workout.model.UserEntity;
import com.example.workout.model.WorkoutLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface WorkoutLogRepository extends JpaRepository<WorkoutLogEntity, String> {
    List<WorkoutLogEntity> findById(Long id);
    List<WorkoutLogEntity> findByDateAndUser(LocalDate date, UserEntity user);
}
