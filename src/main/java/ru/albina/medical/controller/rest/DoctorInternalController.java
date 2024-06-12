package ru.albina.medical.controller.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.albina.backlib.configuration.WebConstants;
import ru.albina.medical.dto.request.DoctorCreate;
import ru.albina.medical.dto.request.DoctorUpdateRequest;
import ru.albina.medical.dto.response.Doctor;
import ru.albina.medical.service.DoctorCreateService;
import ru.albina.medical.service.DoctorFinderService;
import ru.albina.medical.service.DoctorService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(WebConstants.FULL_PRIVATE + "/doctors")
@RequiredArgsConstructor
public class DoctorInternalController {

    private final DoctorCreateService doctorCreateService;

    private final DoctorService doctorService;

    private final DoctorFinderService doctorFinderService;

    @GetMapping
    public List<Doctor> getAll() {
        return this.doctorFinderService.getAll();
    }


    @GetMapping("/{id}")
    public Doctor getAll(@PathVariable("id") UUID id) {
        return this.doctorFinderService.getById(id);
    }

    @PostMapping
    public void create(@RequestBody DoctorCreate doctorCreate) {
        this.doctorCreateService.create(doctorCreate.getUserId());
    }

    @PatchMapping("{id}")
    public void update(@PathVariable("id") UUID userId, @RequestBody DoctorUpdateRequest doctorUpdateRequest) {
        this.doctorCreateService.update(userId, doctorUpdateRequest);
        //TODO CALL DRAFT Scheduler!
    }


    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") UUID id) {
        this.doctorService.deleteById(id);
    }
}
