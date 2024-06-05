package ru.albina.medical.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.albina.medical.domain.DoctorEntity;
import ru.albina.medical.domain.Schedule;
import ru.albina.medical.dto.request.DoctorUpdateRequest;
import ru.albina.medical.mapper.DoctorMapper;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DoctorCreateService {

    private final DoctorService doctorService;

    private final DoctorMapper doctorMapper;

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

    @Transactional
    public void update(UUID userId, DoctorUpdateRequest doctorUpdateRequest) {
        final var doctor = this.doctorService.getById(userId);
        this.doctorMapper.update(doctor, doctorUpdateRequest);
    }
}
