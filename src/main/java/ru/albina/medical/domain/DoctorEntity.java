package ru.albina.medical.domain;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

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

    @Embedded
    private Schedule schedule;

    @Column(name = "hours", nullable = false)
    private Double hours;

    @Size(max = 30)
    @Column(name = "modality", length = 30)
    @Enumerated(EnumType.STRING)
    private Modality modality;


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
    private List<AbsenceSchedule> absenceSchedules;

}