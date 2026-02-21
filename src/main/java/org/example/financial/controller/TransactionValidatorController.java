package org.example.financial.controller;
import org.example.financial.dto.ValidateInputDTO;
import org.example.financial.dto.ValidationResponseDTO;
import org.example.financial.service.TransactionValidatorService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blackrock/challenge/v1")
public class TransactionValidatorController {
    private final TransactionValidatorService service;

    TransactionValidatorController(TransactionValidatorService service){
        this.service=service;
    }
    @PostMapping("/transactions:validator")
    public ValidationResponseDTO validateTransactions(@RequestBody ValidateInputDTO request){
        return service.validateTransactions(request);
    }
}
