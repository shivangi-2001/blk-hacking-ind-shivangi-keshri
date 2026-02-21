package org.example.financial.controller;

import org.example.financial.dto.TemporalConstraintInput;
import org.example.financial.dto.TemporalConstraintOutput;
import org.example.financial.service.TemporalService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blackrock/challenge/v1")
public class TemporalController {
    private final TemporalService service;

    public TemporalController(TemporalService service){
        this.service=service;
    }

    @PostMapping("/transactions:filter")
    public TemporalConstraintOutput filterTransactions(
            @RequestBody TemporalConstraintInput request) {

        return service.applyTemporalRules(request);
    }
}
