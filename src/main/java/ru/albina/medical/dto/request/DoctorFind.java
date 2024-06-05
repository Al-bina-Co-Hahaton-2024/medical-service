package ru.albina.medical.dto.request;

import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class DoctorFind {

    private Set<UUID> userIds;

    private String serviceNumberText;
}
