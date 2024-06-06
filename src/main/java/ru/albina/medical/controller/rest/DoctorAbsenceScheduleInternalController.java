package ru.albina.medical.controller.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.albina.backlib.configuration.WebConstants;
import ru.albina.medical.dto.request.AbsenceScheduleAddRequest;
import ru.albina.medical.service.schedule.AddAbsenceScheduleService;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(WebConstants.FULL_PRIVATE + "/doctors/{id}/absence-schedules")
@RequiredArgsConstructor
public class DoctorAbsenceScheduleInternalController {

    private final AddAbsenceScheduleService addAbsenceScheduleService;

    @PutMapping
    public void add(
            @PathVariable("id") UUID userId,
            @RequestBody AbsenceScheduleAddRequest absenceScheduleAddRequest
    ) {
        this.addAbsenceScheduleService.add(
                userId,
                absenceScheduleAddRequest.getStart(),
                absenceScheduleAddRequest.getEnd()
        );
    }
}
