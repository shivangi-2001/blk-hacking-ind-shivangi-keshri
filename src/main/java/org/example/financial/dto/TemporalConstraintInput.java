package org.example.financial.dto;

import lombok.Data;

import java.util.List;

@Data
public class TemporalConstraintInput {
    private List<QPeriodDTO> q;
    private List<PPeriodDTO> p;
    private List<KPeriodDTO> k;
    private double wage;
    private List<TransactionDTO> transaction;
}
