package ru.albina.medical.mapper;

import org.mapstruct.*;
import ru.albina.medical.configuration.MapperConfiguration;
import ru.albina.medical.domain.DoctorEntity;
import ru.albina.medical.domain.Modality;
import ru.albina.medical.dto.TypeModality;
import ru.albina.medical.dto.request.DoctorUpdateRequest;
import ru.albina.medical.dto.response.Doctor;
import ru.albina.medical.dto.response.Performance;

import java.util.List;

@Mapper(config = MapperConfiguration.class)
public interface DoctorMapper {

    List<Performance> PERFORMANCE_LIST = List.of(
            Performance.builder().modality(Modality.KT).typeModality(TypeModality.DEFAULT).value(15).build(),
            Performance.builder().modality(Modality.KT).typeModality(TypeModality.U).value(16).build(),
            Performance.builder().modality(Modality.KT).typeModality(TypeModality.U2).value(11).build(),
            Performance.builder().modality(Modality.MRT).typeModality(TypeModality.DEFAULT).value(12).build(),
            Performance.builder().modality(Modality.MRT).typeModality(TypeModality.U).value(15).build(),
            Performance.builder().modality(Modality.MRT).typeModality(TypeModality.U2).value(10).build(),
            Performance.builder().modality(Modality.RG).typeModality(TypeModality.DEFAULT).value(49).build(),
            Performance.builder().modality(Modality.FLG).typeModality(TypeModality.DEFAULT).value(181).build(),
            Performance.builder().modality(Modality.MMG).typeModality(TypeModality.DEFAULT).value(49).build(),
            Performance.builder().modality(Modality.DENSITOMETER).typeModality(TypeModality.DEFAULT).value(84).build()
    );

    @Mapping(target = "performances", expression = "java(PERFORMANCE_LIST)")
    Doctor from(DoctorEntity doctor);

    @Mapping(target = "serviceNumber", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "absenceSchedules", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget DoctorEntity doctor, DoctorUpdateRequest doctorUpdateRequest);
}
