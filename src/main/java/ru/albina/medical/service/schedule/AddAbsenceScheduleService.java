package ru.albina.medical.service.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.albina.medical.domain.AbsenceScheduleEntity;
import ru.albina.medical.dto.planner.PlannerOutDateDaysNotification;
import ru.albina.medical.service.DoctorService;
import ru.albina.medical.service.planner.PlannerNotificationService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddAbsenceScheduleService {

    private final DoctorService doctorService;

    private final PlannerNotificationService plannerNotificationService;

    @Transactional
    public void add(UUID userId, LocalDate start, LocalDate end) {
        var doctor = this.doctorService.getById(userId);
        doctor.getAbsenceSchedules().add(
                new AbsenceScheduleEntity()
                        .setId(UUID.randomUUID())
                        .setStart(start)
                        .setEnd(end)
                        .setDoctorEntity(doctor)
        );

        final var merged = this.mergeVacations(new ArrayList<>(doctor.getAbsenceSchedules()));

        doctor.getAbsenceSchedules().removeIf(absenceSchedule -> !merged.contains(absenceSchedule));

        this.plannerNotificationService.notifyAboutOutdatedDays(PlannerOutDateDaysNotification.builder().start(start).end(end).build());
    }


    private List<AbsenceScheduleEntity> mergeVacations(List<AbsenceScheduleEntity> absenceScheduleEntities) {
        if (absenceScheduleEntities == null || absenceScheduleEntities.isEmpty()) {
            return absenceScheduleEntities;
        }

        // Сортировка отпусков по дате начала
        absenceScheduleEntities.sort(Comparator.comparing(AbsenceScheduleEntity::getStart));

        List<AbsenceScheduleEntity> mergedVacations = new ArrayList<>();
        var current = absenceScheduleEntities.get(0);

        for (int i = 1; i < absenceScheduleEntities.size(); i++) {
            AbsenceScheduleEntity next = absenceScheduleEntities.get(i);

            if (current.getEnd().plusDays(1).isAfter(next.getStart()) || current.getEnd().isEqual(next.getStart())) {
                // Объединение текущего отпуска с следующим
                current.setEnd(current.getEnd().isAfter(next.getEnd()) ? current.getEnd() : next.getEnd());
            } else {
                // Добавление текущего отпуска в список и переход к следующему
                mergedVacations.add(current);
                current = next;
            }
        }

        // Добавление последнего интервала
        mergedVacations.add(current);

        return mergedVacations;
    }
}
