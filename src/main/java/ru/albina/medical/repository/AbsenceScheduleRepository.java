package ru.albina.medical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.albina.medical.domain.AbsenceScheduleEntity;

import java.util.UUID;

@Repository
public interface AbsenceScheduleRepository extends JpaRepository<AbsenceScheduleEntity, UUID> {
}
