package ru.albina.medical.dto.response;

import lombok.Data;
import ru.albina.medical.domain.Modality;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
public class Doctor {

    private UUID id;

    private Double hours;

    private LocalDate startContract;

    private LocalDate endContract;

    private Long serviceNumber;

    private Double rate;

    private Modality modality;

    private Set<Modality> optionalModality;
}
