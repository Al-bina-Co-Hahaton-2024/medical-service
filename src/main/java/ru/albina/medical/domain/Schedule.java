package ru.albina.medical.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Embeddable
@Accessors(chain = true)
public class Schedule {

    @Column(name = "work_days")
    private int workDays;

    @Column(name = "weekend_days")
    private int weekendDays;
}
