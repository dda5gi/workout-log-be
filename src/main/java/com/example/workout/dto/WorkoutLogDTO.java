package com.example.workout.dto;

import com.example.workout.model.UserEntity;
import com.example.workout.model.WorkoutLogEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder //builder 클래스를 따로 작성치 않아도 패턴을 통해 오브젝트 생성 가능
@NoArgsConstructor
@AllArgsConstructor
@Data //getter setter 메서드 구현해줌
public class WorkoutLogDTO {
    private Long id;
    private LocalDate date;
    private int setOrder;
    private String target;
    private String name;
    private String weight;
    private String reps;

    public WorkoutLogDTO(final WorkoutLogEntity entity) {
        this.id = entity.getId();
        this.date = entity.getDate();
        this.setOrder = entity.getSetOrder();
        this.target = entity.getTarget();
        this.name = entity.getName();
        this.weight = entity.getWeight();
        this.reps = entity.getReps();
    }

    public static WorkoutLogEntity toEntity(final WorkoutLogDTO dto, UserEntity userEntity) {
        return WorkoutLogEntity.builder()
                .id(dto.getId())
                .user(userEntity)
                .date(dto.getDate())
                .setOrder(dto.getSetOrder())
                .target(dto.getTarget())
                .name(dto.getName())
                .weight(dto.getWeight())
                .reps(dto.getReps())
                .build();
    }
}
