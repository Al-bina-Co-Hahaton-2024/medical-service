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

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DoctorFinderService {
    private final DoctorMapper doctorMapper;
    private final DoctorRepository doctorRepository;


    @Transactional(readOnly = true)
    public Page<Doctor> find(DoctorFind doctorFind, Pageable pageable) {
        Specification<DoctorEntity> specification = Specification.where(null);

        if (doctorFind.getServiceNumberText() != null) {
            specification = specification.or(((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(
                            root.get(DoctorEntity_.serviceNumber).as(String.class),
                            "%" + doctorFind.getServiceNumberText() + "%"
                    )
            ));
        }

        if (doctorFind.getUserIds() != null) {
            specification = specification.or(
                    (root, query, criteriaBuilder) -> root.get(DoctorEntity_.id).in(doctorFind.getUserIds())
            );
        }

        final var result = this.doctorRepository.findAll(specification, pageable);

        return result.map(this.doctorMapper::from);
    }

    public List<Doctor> getAll() {
        return this.doctorRepository.findAll()
                .stream().map(this.doctorMapper::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<Doctor> findByIds(Set<UUID> ids) {
        return this.doctorRepository.findAllById(ids).stream()
                .map(this.doctorMapper::from)
                .toList();
    }
}
