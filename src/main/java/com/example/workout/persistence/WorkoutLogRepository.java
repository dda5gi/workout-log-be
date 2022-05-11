package com.example.workout.persistence;

import com.example.workout.model.UserEntity;
import com.example.workout.model.WorkoutLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WorkoutLogRepository extends JpaRepository<WorkoutLogEntity, Long> {
    Optional<WorkoutLogEntity> findById(Long id);
    List<WorkoutLogEntity> findByDate(LocalDate date);
    List<WorkoutLogEntity> findByDateAndUser(LocalDate date, UserEntity user);
}
