package org.example.financial.controller;

import org.example.financial.dto.ExpenseDTO;
import org.example.financial.dto.TransactionDTO;
import org.example.financial.service.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/blackrock/challenge/v1")
public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService service){
        this.service = service;
    }

    @PostMapping("/transactions:parse")
    public List<TransactionDTO> parseTranscations(@RequestBody List<ExpenseDTO> expenses){
        return service.parseTransaction(expenses);
    }
}
