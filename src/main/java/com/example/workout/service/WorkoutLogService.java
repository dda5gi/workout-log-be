package com.example.workout.service;

import com.example.workout.model.UserEntity;
import com.example.workout.model.WorkoutLogEntity;
import com.example.workout.persistence.WorkoutLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class WorkoutLogService {
    @Autowired
    private WorkoutLogRepository repository;
    //validate 메소드 추가

    public List<WorkoutLogEntity> retrieve(final LocalDate date, final UserEntity user) {
        List<WorkoutLogEntity> entity = repository.findByDateAndUser(date, user);
        log.info("workoutLog retrieved : ", date);
        return entity;
    }

    public List<WorkoutLogEntity> create(final WorkoutLogEntity entity) {
        repository.save(entity);
        log.info("Entity Id : {} is saved", entity.getId());
        return repository.findById(entity.getId());
    }

    public void delete(final WorkoutLogEntity entity) {
        repository.delete(entity);
    }
}
