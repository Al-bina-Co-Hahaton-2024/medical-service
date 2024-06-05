package ru.albina.medical.dto.request;

import lombok.Data;
import ru.albina.medical.domain.Modality;

@Data
public class DoctorFind {

    private Modality modality;
}
