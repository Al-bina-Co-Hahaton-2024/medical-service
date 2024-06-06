package ru.albina.medical.dto.request;

import lombok.Data;
import ru.albina.medical.domain.Modality;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Data
public class DoctorUpdateRequest {

    private Double rate;

    private List<DayOfWeek> workDays;

    private Double hours;

    private Modality modality;

    private LocalDate startContract;

    private LocalDate endContract;

    private List<Modality> optionalModality;
}
