package com.example.workout.service;

import com.example.workout.model.UserEntity;
import com.example.workout.model.WorkoutLogEntity;
import com.example.workout.persistence.WorkoutLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    public WorkoutLogEntity create(final WorkoutLogEntity entity) {
        repository.save(entity);
        log.info("Entity Id : {} is saved", entity.getId());
        return repository.findById(entity.getId()).get();
    }

    public void delete(final WorkoutLogEntity entity) {
        repository.delete(entity);
    }

    public WorkoutLogEntity update(final WorkoutLogEntity entity) {
        final Optional<WorkoutLogEntity> original = repository.findById(entity.getId());
        original.ifPresent(workoutLog -> {
            workoutLog.setId(entity.getId());
            workoutLog.setUser(entity.getUser());
            workoutLog.setDate(entity.getDate());
            workoutLog.setName(entity.getName());
            workoutLog.setReps(entity.getReps());
            workoutLog.setTarget(entity.getTarget());
            workoutLog.setSetOrder(entity.getSetOrder());
            repository.save(workoutLog);
        });

        return repository.findById(entity.getId()).get();
    }
}
