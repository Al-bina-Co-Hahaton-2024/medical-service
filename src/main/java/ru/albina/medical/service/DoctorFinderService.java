package ru.albina.medical.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.albina.medical.domain.DoctorEntity;
import ru.albina.medical.domain.DoctorEntity_;
import ru.albina.medical.dto.request.DoctorFind;
import ru.albina.medical.dto.response.Doctor;
import ru.albina.medical.mapper.DoctorMapper;
import ru.albina.medical.repository.DoctorRepository;

@Service
@RequiredArgsConstructor
public class DoctorFinderService {
    private final DoctorMapper doctorMapper;
    private final DoctorRepository doctorRepository;


    @Transactional(readOnly = true)
    public Page<Doctor> find(DoctorFind doctorFind, Pageable pageable) {
        Specification<DoctorEntity> specification = Specification.where(null);

        if (doctorFind.getModality() != null) {
            specification = specification.and(((root, query, criteriaBuilder) -> criteriaBuilder.equal(
                    root.get(DoctorEntity_.modality), doctorFind.getModality())
            ));
        }

        final var result = this.doctorRepository.findAll(specification, pageable);

        return result.map(this.doctorMapper::from);
    }
}
