package ru.albina.medical.dto.request;

import lombok.Data;
import ru.albina.medical.domain.Modality;

import java.util.Set;
import java.util.UUID;

@Data
public class DoctorFind {

    private String fullName;

    private Set<UUID> userIds;

    private String serviceNumberText;

    private Modality modality;
}
