package ru.albina.medical.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.albina.backlib.configuration.WebConstants;
import ru.albina.medical.dto.response.Performance;
import ru.albina.medical.service.PerformanceService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(WebConstants.FULL_PRIVATE + "/performances/average")
@RequiredArgsConstructor
public class PerformanceController {

    private final PerformanceService performanceService;

    @Operation(
            summary = "Получить среднюю производительность",
            responses = {
                    @ApiResponse(
                            description = "ОК",
                            responseCode = "200",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Performance.class)))
                    )
            }
    )
    @GetMapping
    public List<Performance> getAveragePerformance() {
        return this.performanceService.getAveragePerformance();
    }
}
