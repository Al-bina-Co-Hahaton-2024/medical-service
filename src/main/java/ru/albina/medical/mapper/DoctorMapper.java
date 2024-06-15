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

    Float PERFORMANCE_HOURS = 8f;

    List<Performance> PERFORMANCE_LIST = List.of(
            Performance.builder().modality(Modality.KT).typeModality(TypeModality.DEFAULT).value(Math.round(28 / PERFORMANCE_HOURS)).build(),
            Performance.builder().modality(Modality.KT).typeModality(TypeModality.U).value(Math.round(17 / PERFORMANCE_HOURS)).build(),
            Performance.builder().modality(Modality.KT).typeModality(TypeModality.U2).value(Math.round(12 / PERFORMANCE_HOURS)).build(),
            Performance.builder().modality(Modality.MRT).typeModality(TypeModality.DEFAULT).value(Math.round(21 / PERFORMANCE_HOURS)).build(),
            Performance.builder().modality(Modality.MRT).typeModality(TypeModality.U).value(Math.round(16 / PERFORMANCE_HOURS)).build(),
            Performance.builder().modality(Modality.MRT).typeModality(TypeModality.U2).value(Math.round(10 / PERFORMANCE_HOURS)).build(),
            Performance.builder().modality(Modality.RG).typeModality(TypeModality.DEFAULT).value(Math.round(87 / PERFORMANCE_HOURS)).build(),
            Performance.builder().modality(Modality.FLG).typeModality(TypeModality.DEFAULT).value(Math.round(316 / PERFORMANCE_HOURS)).build(),
            Performance.builder().modality(Modality.MMG).typeModality(TypeModality.DEFAULT).value(Math.round(87 / PERFORMANCE_HOURS)).build(),
            Performance.builder().modality(Modality.DENSITOMETER).typeModality(TypeModality.DEFAULT).value(Math.round(147 / PERFORMANCE_HOURS)).build()
    );

    @Mapping(target = "performances", expression = "java(PERFORMANCE_LIST)")
    Doctor from(DoctorEntity doctor);

    @Mapping(target = "serviceNumber", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "absenceSchedules", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget DoctorEntity doctor, DoctorUpdateRequest doctorUpdateRequest);
}
