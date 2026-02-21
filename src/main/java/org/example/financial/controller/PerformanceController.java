package org.example.financial.controller;

import org.example.financial.dto.PerformanceDTO;
import org.example.financial.dto.ReturnsRequestDTO;
import org.example.financial.service.PerformanceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blackrock/challenge/v1")
public class PerformanceController {

    private final PerformanceService service;

    public PerformanceController(PerformanceService service) {
        this.service = service;
    }

    @PostMapping("/performance:nps")
    public PerformanceDTO performanceNPS(
            @RequestBody ReturnsRequestDTO request) {

        return service.measurePerformance(request,
                0.0711, true);
    }

    @PostMapping("/performance:index")
    public PerformanceDTO performanceIndex(
            @RequestBody ReturnsRequestDTO request) {

        return service.measurePerformance(request,
                0.1449, false);
    }
}