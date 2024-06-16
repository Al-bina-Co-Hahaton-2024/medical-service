package ru.albina.medical.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.albina.backlib.configuration.WebConstants;
import ru.albina.backlib.configuration.auto.OpenApiConfiguration;
import ru.albina.medical.dto.request.DoctorFind;
import ru.albina.medical.dto.response.Doctor;
import ru.albina.medical.service.DoctorFinderService;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(WebConstants.FULL_WEB + "/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorFinderService doctorFinderService;


    @Operation(
            summary = "Поиск докторов (не четкий поиск)",
            security = @SecurityRequirement(name = OpenApiConfiguration.JWT),
            responses = {
                    @ApiResponse(
                            description = "ОК",
                            responseCode = "200"
                    )
            }
    )
    //TODO @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public Page<Doctor> findDoctor(
            DoctorFind doctorFind,
            Pageable pageable
    ) {
        return this.doctorFinderService.find(doctorFind, pageable);
    }

    @Operation(
            summary = "Поиск докторов (не четкий поиск)",
            security = @SecurityRequirement(name = OpenApiConfiguration.JWT),
            responses = {
                    @ApiResponse(
                            description = "ОК",
                            responseCode = "200"
                    )
            }
    )
    //TODO @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public Page<Doctor> findDoctorPost(
            @RequestBody DoctorFind doctorFind,
            Pageable pageable
    ) {
        return this.doctorFinderService.find(doctorFind, pageable);
    }

    @Operation(
            summary = "Всех докторов",
            security = @SecurityRequirement(name = OpenApiConfiguration.JWT),
            responses = {
                    @ApiResponse(
                            description = "ОК",
                            responseCode = "200"
                    )
            }
    )
    //TODO @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/")
    public List<Doctor> findDoctor() {
        return this.doctorFinderService.getAll();
    }


    @Operation(
            summary = "Поиск докторов по фильтрам без Page, но строгий поиск",
            security = @SecurityRequirement(name = OpenApiConfiguration.JWT),
            responses = {
                    @ApiResponse(
                            description = "ОК",
                            responseCode = "200"
                    )
            }
    )
    //TODO @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/find")
    public List<Doctor> findDoctor(
            @RequestBody DoctorFind doctorFind
    ) {
        return this.doctorFinderService.find(doctorFind);
    }


    @Operation(
            summary = "Поиск докторов по IDs",
            security = @SecurityRequirement(name = OpenApiConfiguration.JWT),
            responses = {
                    @ApiResponse(
                            description = "ОК",
                            responseCode = "200"
                    )
            }
    )
    //TODO @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/find-by-ids")
    public List<Doctor> findDoctorByIds(
            @RequestBody Set<UUID> ids
    ) {
        return this.doctorFinderService.findByIds(ids);
    }

}
