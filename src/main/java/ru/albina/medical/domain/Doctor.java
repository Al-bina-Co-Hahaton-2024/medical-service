package ru.albina.medical.domain;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @Column(name = "user_id", nullable = false)
    private UUID id;

    @Size(max = 100)
    @Column(name = "full_name", length = 100)
    private String fullName;

    @NotNull
    @Column(name = "rate", nullable = false)
    private Double rate;

    @Size(max = 30)
    @NotNull
    @Column(name = "modality", nullable = false, length = 30)
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
    @Column(name = "optional_modality",columnDefinition = "_varchar")
    private List<Modality> optionalModality;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AbsenceSchedule> absenceSchedules;

}