package ru.albina.medical.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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

@Slf4j
@RestController
@RequestMapping(WebConstants.FULL_WEB + "/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorFinderService doctorFinderService;


    @Operation(
            summary = "Поиск докторов",
            security = @SecurityRequirement(name = OpenApiConfiguration.JWT),
            responses = {
                    @ApiResponse(
                            description = "ОК",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Page.class))
                    )
            }
    )
    //TODO @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public Page<Doctor> createUser(
           DoctorFind doctorFind,
           Pageable pageable
    ) {
        return this.doctorFinderService.find(doctorFind, pageable);
    }

}
