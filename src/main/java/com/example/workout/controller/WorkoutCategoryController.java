package com.example.workout.controller;

import com.example.workout.dto.ResponseDTO;
import com.example.workout.dto.WorkoutCatergoryDTO;
import com.example.workout.model.WorkoutCategoryEntity;
import com.example.workout.persistence.UserRepository;
import com.example.workout.service.WorkoutCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("workoutcategory")
public class WorkoutCategoryController {
    @Autowired
    private WorkoutCategoryService service;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<?> retrieveWorkoutCategory(@AuthenticationPrincipal String userId) {
        // 기본분류 운동종류 + 커스텀운동 리턴
        List<WorkoutCategoryEntity> originalData = service.retrieveOriginal();
        List<WorkoutCategoryEntity> customData = service.retrieveCustomData(userId);
        List<WorkoutCategoryEntity> entities = new ArrayList<>();
        entities.addAll(originalData);
        entities.addAll(customData);
        List<WorkoutCatergoryDTO> workoutCatergoryDtos = entities.stream().map(WorkoutCatergoryDTO::new).collect(Collectors.toList());
        ResponseDTO<WorkoutCatergoryDTO> response = ResponseDTO.<WorkoutCatergoryDTO>builder().data(workoutCatergoryDtos).build();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<?> createWorkoutCategory(@AuthenticationPrincipal String userId, @RequestBody WorkoutCatergoryDTO workoutCatergoryDTO) {
        // 커스텀 운동 등록
        WorkoutCategoryEntity entity = WorkoutCatergoryDTO.toEntity(workoutCatergoryDTO, userRepository.findById(userId).get());
        entity.setId(null);
        service.create(entity);
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteWorkoutCategory(@AuthenticationPrincipal String userId, @RequestBody WorkoutCatergoryDTO workoutCatergoryDTO) {
        WorkoutCategoryEntity entity = WorkoutCatergoryDTO.toEntity(workoutCatergoryDTO, userRepository.findById(userId).get());
        service.delete(entity);
        return ResponseEntity.ok().body(null);
    }
}
