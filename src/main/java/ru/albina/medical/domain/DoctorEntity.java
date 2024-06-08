package ru.albina.medical.domain;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "doctor")
@Accessors(chain = true)
public class DoctorEntity {
    @Id
    @Column(name = "user_id", nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "rate", nullable = false)
    private Double rate;

    @Type(
            value = ListArrayType.class,
            parameters = {
                    @Parameter(
                            name = ListArrayType.SQL_ARRAY_TYPE,
                            value = "varchar"
                    )
            }
    )
    @Column(name = "work_days", columnDefinition = "_varchar")
    private List<DayOfWeek> workDays;

    @Column(name = "hours", nullable = false)
    private Double hours;

    @Size(max = 30)
    @Column(name = "modality", length = 30)
    @Enumerated(EnumType.STRING)
    private Modality modality;

    @Column(name = "start_contract")
    private LocalDate startContract;

    @Column(name = "end_contract")
    private LocalDate endContract;

    @Column(name = "service_number", insertable = false, updatable = false)
    @Generated(GenerationTime.INSERT)
    private Long serviceNumber;

    @Column(name = "start_work_day")
    private LocalTime startWorkDay;

    @Type(
            value = ListArrayType.class,
            parameters = {
                    @Parameter(
                            name = ListArrayType.SQL_ARRAY_TYPE,
                            value = "varchar"
                    )
            }
    )
    @Column(name = "optional_modality", columnDefinition = "_varchar")
    private List<Modality> optionalModality;

    @OneToMany(mappedBy = "doctorEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AbsenceScheduleEntity> absenceSchedules;

}