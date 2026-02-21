package org.example.financial.dto;

import lombok.Data;

import java.util.List;

@Data
public class ValidateInputDTO {
    private List<TransactionDTO> transactions;
    private double wage;
}
