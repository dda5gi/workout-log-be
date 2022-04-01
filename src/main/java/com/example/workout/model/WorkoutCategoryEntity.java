package com.example.workout.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "WorkoutCategory")
public class WorkoutCategoryEntity {
    @Id
    @Column(nullable = false)
    private String name;

    private String target;
}
