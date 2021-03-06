package com.example.workout.service;

import com.example.workout.error.exception.EntityNotFoundException;
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
        repository.findById(entity.getId()).orElseThrow(() -> new EntityNotFoundException());
        int setOrder = entity.getSetOrder();
        List<WorkoutLogEntity> workoutLogEntities = repository.findByDate(entity.getDate());
        // 엔티티 삭제하기 전에 이후 세트들의 세트 순서를 1씩 낮춰줌
        for (WorkoutLogEntity e : workoutLogEntities) {
            if(setOrder < e.getSetOrder()) {
                e.setSetOrder(e.getSetOrder()-1);
                update(e);
            }
        }
        repository.delete(entity);
    }

    public WorkoutLogEntity update(final WorkoutLogEntity entity) {
        repository.findById(entity.getId()).orElseThrow(() -> new EntityNotFoundException());
        repository.save(entity);
        return repository.findById(entity.getId()).get();
    }
}
