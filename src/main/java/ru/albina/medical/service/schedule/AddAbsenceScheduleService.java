package ru.albina.medical.service.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.albina.medical.domain.AbsenceSchedule;
import ru.albina.medical.service.DoctorService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddAbsenceScheduleService {

    private final DoctorService doctorService;

    @Transactional
    public void add(UUID userId, LocalDate start, LocalDate end) {
        var doctor = this.doctorService.getById(userId);
        doctor.getAbsenceSchedules().add(
                new AbsenceSchedule()
                        .setId(UUID.randomUUID())
                        .setStart(start)
                        .setEnd(end)
                        .setDoctorEntity(doctor)
        );

        final var merged = this.mergeVacations(new ArrayList<>(doctor.getAbsenceSchedules()));

        doctor.getAbsenceSchedules().removeIf(absenceSchedule -> !merged.contains(absenceSchedule));
    }


    private List<AbsenceSchedule> mergeVacations(List<AbsenceSchedule> absenceSchedules) {
        if (absenceSchedules == null || absenceSchedules.isEmpty()) {
            return absenceSchedules;
        }

        // Сортировка отпусков по дате начала
        absenceSchedules.sort(Comparator.comparing(AbsenceSchedule::getStart));

        List<AbsenceSchedule> mergedVacations = new ArrayList<>();
        var current = absenceSchedules.get(0);

        for (int i = 1; i < absenceSchedules.size(); i++) {
            AbsenceSchedule next = absenceSchedules.get(i);

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
