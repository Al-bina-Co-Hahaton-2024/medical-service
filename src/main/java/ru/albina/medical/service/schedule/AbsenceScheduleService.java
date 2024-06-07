package ru.albina.medical.service.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.albina.medical.domain.AbsenceScheduleEntity;
import ru.albina.medical.repository.AbsenceScheduleRepository;

@Service
@RequiredArgsConstructor
public class AbsenceScheduleService {

    private final AbsenceScheduleRepository absenceScheduleRepository;

    @Transactional
    public AbsenceScheduleEntity save(AbsenceScheduleEntity absenceScheduleEntity) {
        return this.absenceScheduleRepository.save(absenceScheduleEntity);
    }
}
