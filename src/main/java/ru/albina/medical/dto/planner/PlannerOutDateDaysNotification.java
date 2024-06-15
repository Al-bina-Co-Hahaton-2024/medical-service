package ru.albina.medical.dto.planner;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlannerOutDateDaysNotification {

    private LocalDate start;

    private LocalDate end;
}
