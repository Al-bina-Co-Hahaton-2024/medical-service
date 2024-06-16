package ru.albina.medical.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.albina.medical.client.UserClient;
import ru.albina.medical.domain.DoctorEntity;
import ru.albina.medical.domain.DoctorEntity_;
import ru.albina.medical.dto.request.DoctorFind;
import ru.albina.medical.dto.response.Doctor;
import ru.albina.medical.exception.EntityNotFoundException;
import ru.albina.medical.mapper.DoctorMapper;
import ru.albina.medical.repository.DoctorRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DoctorFinderService {

    private final UserClient userClient;
    private final DoctorMapper doctorMapper;
    private final DoctorRepository doctorRepository;


    @Transactional(readOnly = true)
    public Page<Doctor> find(DoctorFind doctorFind, Pageable pageable) {
        return this.doctorRepository.findAll(this.build(doctorFind, false), pageable)
                .map(this.doctorMapper::from);
    }


    @Transactional(readOnly = true)
    public List<Doctor> find(DoctorFind doctorFind) {
        return this.doctorRepository.findAll(this.build(doctorFind, true)).stream()
                .map(this.doctorMapper::from)
                .toList();
    }

    private Specification<DoctorEntity> build(DoctorFind doctorFind, boolean strict) {
        Specification<DoctorEntity> specification = Specification.where(null);

        if (doctorFind.getFullName() != null) {
            final var result = this.userClient.findByFullName(doctorFind.getFullName());
            if (!result.isEmpty()) {
                final var ids = new HashSet<>(Optional.ofNullable(doctorFind.getUserIds()).orElse(Set.of()));
                ids.addAll(result);
                doctorFind.setUserIds(ids);
            }
        }

        if (doctorFind.getServiceNumberText() != null) {
            specification = this.merge(
                    specification,
                    (root, query, criteriaBuilder) ->
                            criteriaBuilder.like(
                                    root.get(DoctorEntity_.serviceNumber).as(String.class),
                                    "%" + doctorFind.getServiceNumberText() + "%"
                            ),
                    strict
            );
        }

        if (doctorFind.getUserIds() != null) {
            specification = this.merge(
                    specification,
                    (root, query, criteriaBuilder) -> root.get(DoctorEntity_.id).in(doctorFind.getUserIds()),
                    strict
            );
        }

        if (doctorFind.getModality() != null) {
            specification = this.merge(
                    specification,
                    (root, query, criteriaBuilder) -> root.get(DoctorEntity_.modality).in(doctorFind.getModality()),
                    strict
            );
        }
        return specification;
    }

    private Specification<DoctorEntity> merge(Specification<DoctorEntity> root, Specification<DoctorEntity> merged, boolean strict) {
        if (strict) {
            return root.and(merged);
        } else {
            return root.or(merged);
        }
    }

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    public Doctor getById(UUID id) {
        return this.doctorRepository.findById(id)
                .map(this.doctorMapper::from)
                .orElseThrow(
                        () -> new EntityNotFoundException("Doctor with id " + id + " not found")
                );
    }
}
