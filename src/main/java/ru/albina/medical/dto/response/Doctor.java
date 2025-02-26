package ru.albina.medical.dto.response;

import lombok.Data;
import ru.albina.medical.domain.Modality;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
public class Doctor {

    private UUID id;

    private List<DayOfWeek> workDays;

    private Double hours;

    private LocalDate startContract;

    private LocalDate endContract;

    private Long serviceNumber;

    private Double rate;

    private Modality modality;

    private Set<Modality> optionalModality;

    private List<Performance> performances;

    private List<AbsenceSchedule> absenceSchedules;

    private LocalTime startWorkDay;
}
