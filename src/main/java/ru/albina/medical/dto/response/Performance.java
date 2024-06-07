package ru.albina.medical.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.albina.medical.domain.Modality;
import ru.albina.medical.dto.TypeModality;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Performance {

    private Modality modality;

    private TypeModality typeModality;

    private int value;
}
