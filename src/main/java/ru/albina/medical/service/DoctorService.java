package ru.albina.medical.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.albina.medical.domain.DoctorEntity;
import ru.albina.medical.exception.EntityNotFoundException;
import ru.albina.medical.repository.DoctorRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public DoctorEntity save(DoctorEntity doctor) {
        return this.doctorRepository.save(doctor);
    }

    @Transactional
    public void deleteById(UUID id) {
        this.doctorRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public DoctorEntity getById(UUID userId) {
        return this.doctorRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("Can't find doctors by id " + userId)
        );
    }
}
