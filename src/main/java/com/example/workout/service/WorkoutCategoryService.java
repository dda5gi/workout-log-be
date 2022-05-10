package com.example.workout.service;

import com.example.workout.error.exception.EntityNotFoundException;
import com.example.workout.model.WorkoutCategoryEntity;
import com.example.workout.persistence.WorkoutCategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class WorkoutCategoryService {
    @Autowired
    private WorkoutCategoryRepository repository;

    public List<WorkoutCategoryEntity> retrieveOriginal() {
        List<WorkoutCategoryEntity> entities = repository.findByOriginal(true);
        return entities;
    }

    public List<WorkoutCategoryEntity> retrieveCustomData(final String userId) {
        List<WorkoutCategoryEntity> entities = repository.findByUserId(userId);
        return entities;
    }

    public WorkoutCategoryEntity create(final WorkoutCategoryEntity entity) {
        repository.save(entity);
        log.info("Entity Id : {} is saved", entity.getId());
        return repository.findById(entity.getId()).get();
    }

    public void delete(final WorkoutCategoryEntity entity) {
        repository.findById(entity.getId()).orElseThrow(() -> new EntityNotFoundException());
        repository.delete(entity); }
}
