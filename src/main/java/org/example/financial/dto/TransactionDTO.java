package org.example.financial.dto;

import lombok.Data;

@Data
public class TransactionDTO {
    private String date;
    private double amount;
    private double ceiling;
    private double remanent;
}
