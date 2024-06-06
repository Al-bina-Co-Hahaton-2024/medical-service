package ru.albina.medical.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AbsenceScheduleAddRequest {

    private LocalDate start;

    private LocalDate end;
}
