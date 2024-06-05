package ru.albina.medical.mapper;

import org.mapstruct.Mapper;
import ru.albina.medical.configuration.MapperConfiguration;
import ru.albina.medical.domain.DoctorEntity;
import ru.albina.medical.dto.response.Doctor;

@Mapper(config = MapperConfiguration.class)
public interface DoctorMapper {

    Doctor from(DoctorEntity doctor);
}
