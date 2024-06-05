package ru.albina.medical.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.albina.medical.domain.DoctorEntity;
import ru.albina.medical.domain.Schedule;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DoctorCreateService {

    private final DoctorService doctorService;

    @Transactional
    public void create(UUID userId) {
        final var entity = new DoctorEntity().setId(userId)
                .setRate(1.0)
                .setHours(8.0)
                .setSchedule(
                        new Schedule()
                                .setWorkDays(5)
                                .setWeekendDays(2)
                );

        this.doctorService.save(entity);
    }
}
