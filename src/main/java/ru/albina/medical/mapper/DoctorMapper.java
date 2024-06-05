package ru.albina.medical.mapper;

import org.mapstruct.*;
import ru.albina.medical.configuration.MapperConfiguration;
import ru.albina.medical.domain.DoctorEntity;
import ru.albina.medical.dto.request.DoctorUpdateRequest;
import ru.albina.medical.dto.response.Doctor;

@Mapper(config = MapperConfiguration.class)
public interface DoctorMapper {

    Doctor from(DoctorEntity doctor);

    @Mapping(target = "serviceNumber", ignore = true)
    @Mapping(target = "schedule.workDays", source = "workDays")
    @Mapping(target = "schedule.weekendDays", source = "weekendDays")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "absenceSchedules", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget DoctorEntity doctor, DoctorUpdateRequest doctorUpdateRequest);
}
