package ru.albina.medical.controller.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.albina.backlib.configuration.WebConstants;
import ru.albina.medical.dto.request.DoctorCreate;
import ru.albina.medical.service.DoctorCreateService;
import ru.albina.medical.service.DoctorService;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(WebConstants.FULL_PRIVATE + "/doctors")
@RequiredArgsConstructor
public class DoctorInternalController {

    private final DoctorCreateService doctorCreateService;

    private final DoctorService doctorService;

    @PostMapping
    public void create(@RequestBody DoctorCreate doctorCreate) {
        this.doctorCreateService.create(doctorCreate.getUserId());
    }


    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") UUID id) {
        this.doctorService.deleteById(id);
    }
}
