package ru.albina.medical.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AbsenceSchedule {

    private LocalDate start;

    private LocalDate end;
}
