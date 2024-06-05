package ru.albina.medical.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class DoctorCreate {

    private UUID userId;
}
