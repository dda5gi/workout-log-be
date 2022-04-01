package com.example.workout.dto;

import com.example.workout.model.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder //builder 클래스를 따로 작성치 않아도 패턴을 통해 오브젝트 생성 가능
@NoArgsConstructor
@AllArgsConstructor
@Data //getter setter 메서드 구현해줌
public class TodoDTO {
    private String id;
    private String title;
    private boolean done;

    public TodoDTO(final TodoEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.done = entity.isDone();
    }

    public static TodoEntity toEntity(final com.example.workout.dto.TodoDTO dto) {
        return TodoEntity.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .done(dto.isDone())
                .build();
    }
}
