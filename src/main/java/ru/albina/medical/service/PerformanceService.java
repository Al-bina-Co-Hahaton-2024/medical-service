package ru.albina.medical.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.albina.medical.dto.response.Performance;
import ru.albina.medical.mapper.DoctorMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerformanceService {

    public List<Performance> getAveragePerformance(){
        return DoctorMapper.PERFORMANCE_LIST;
    }
}
