package com.example.workout.dto;

import com.example.workout.model.UserEntity;
import com.example.workout.model.WorkoutCategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WorkoutCatergoryDTO {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String target;

    public WorkoutCatergoryDTO(final WorkoutCategoryEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.target = entity.getTarget();
    }

    public static WorkoutCategoryEntity toEntity(final WorkoutCatergoryDTO dto, UserEntity userEntity) {
        return WorkoutCategoryEntity.builder()
                .id(dto.getId())
                .user(userEntity)
                .original(false)
                .name(dto.getName())
                .target(dto.getTarget())
                .build();
    }
}
