package ru.albina.medical.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "absence_schedule")
public class AbsenceSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "absence_schedule_id_gen")
    @SequenceGenerator(name = "absence_schedule_id_gen", sequenceName = "absence_schedule_sequence", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "start", nullable = false)
    private Instant start;

    @NotNull
    @Column(name = "\"end\"", nullable = false)
    private Instant end;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id", referencedColumnName = "user_id")
    private DoctorEntity doctorEntity;

}