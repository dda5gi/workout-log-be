package com.example.workout.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "WorkoutLog")
public class WorkoutLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private int set_order;

    private String target;

    @Column(nullable = false)
    private String name;

    private String weight;

    @Column(nullable = false)
    private String reps;
}
