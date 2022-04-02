package com.example.workout.controller;

import com.example.workout.dto.ResponseDTO;
import com.example.workout.dto.WorkoutLogDTO;
import com.example.workout.model.WorkoutLogEntity;
import com.example.workout.persistence.UserRepository;
import com.example.workout.service.WorkoutLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("workoutlog")
public class WorkoutLogController {
    @Autowired
    private WorkoutLogService service;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<?> retrieveWorkoutLog(@AuthenticationPrincipal String userId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        List<WorkoutLogEntity> entities = service.retrieve(date, userRepository.findById(userId).get());
        List<WorkoutLogDTO> dtos = entities.stream().map(WorkoutLogDTO::new).collect(Collectors.toList());
        ResponseDTO<WorkoutLogDTO> response = ResponseDTO.<WorkoutLogDTO>builder().data(dtos).build();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<?> createWorkoutLog(@AuthenticationPrincipal String userId, @RequestBody WorkoutLogDTO workoutLogDTO) {
        WorkoutLogEntity entity = WorkoutLogDTO.toEntity(workoutLogDTO, userRepository.findById(userId).get());
        entity.setId(null);
        List<WorkoutLogEntity> entities = service.create(entity);
        List<WorkoutLogDTO> dtos = entities.stream().map(WorkoutLogDTO::new).collect(Collectors.toList());
        ResponseDTO<WorkoutLogDTO> response = ResponseDTO.<WorkoutLogDTO>builder().data(dtos).build();
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteWorkoutLog(@AuthenticationPrincipal String userId, @RequestBody WorkoutLogDTO workoutLogDTO) {
        WorkoutLogEntity entity = WorkoutLogDTO.toEntity(workoutLogDTO, userRepository.findById(userId).get());
        service.delete(entity);
        return ResponseEntity.ok().body(null);
    }
}
