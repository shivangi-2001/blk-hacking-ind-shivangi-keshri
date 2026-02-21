package org.example.financial.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReturnsRequestDTO {
    private int age;
    private double wage;
    private double inflation;

    private List<QPeriodDTO> q = new ArrayList<>();
    private List<PPeriodDTO> p = new ArrayList<>();
    private List<KPeriodDTO> k = new ArrayList<>();

    private List<TransactionDTO> transactions;
}
