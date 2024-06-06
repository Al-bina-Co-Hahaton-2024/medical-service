package ru.albina.medical.service.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.albina.medical.domain.AbsenceSchedule;
import ru.albina.medical.repository.AbsenceScheduleRepository;

@Service
@RequiredArgsConstructor
public class AbsenceScheduleService {

    private final AbsenceScheduleRepository absenceScheduleRepository;

    @Transactional
    public AbsenceSchedule save(AbsenceSchedule absenceSchedule) {
        return this.absenceScheduleRepository.save(absenceSchedule);
    }
}
