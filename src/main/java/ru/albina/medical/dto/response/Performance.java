package ru.albina.medical.dto.response;

import lombok.Builder;
import ru.albina.medical.domain.Modality;
import ru.albina.medical.dto.TypeModality;

@Builder
public class Performance {

    private Modality modality;

    private TypeModality typeModality;

    private int value;
}
