package ru.albina.medical.dto.response;

import lombok.Data;
import ru.albina.medical.domain.Modality;

import java.util.Set;
import java.util.UUID;

@Data
public class Doctor {

    private UUID id;

    private Double rate;

    private Modality modality;

    private Set<Modality> optionalModality;
}
