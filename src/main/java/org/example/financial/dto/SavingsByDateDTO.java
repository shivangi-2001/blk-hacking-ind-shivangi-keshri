package org.example.financial.dto;

import lombok.Data;

@Data
public class SavingsByDateDTO {

    private String start;
    private String end;
    private double amount;
    private double profits;
    private double taxBenefit;
}