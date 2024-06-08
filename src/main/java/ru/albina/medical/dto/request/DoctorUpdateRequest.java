package ru.albina.medical.dto.request;

import lombok.Data;
import ru.albina.medical.domain.Modality;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Data
public class DoctorUpdateRequest {

    private Double rate;

    private Set<DayOfWeek> workDays;

    private Double hours;

    private Modality modality;

    private LocalDate startContract;

    private LocalDate endContract;

    private List<Modality> optionalModality;

    private LocalTime startWorkDay;
}
