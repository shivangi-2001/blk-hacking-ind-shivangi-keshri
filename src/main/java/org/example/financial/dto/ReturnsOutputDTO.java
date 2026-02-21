package org.example.financial.dto;

import lombok.Data;
import java.util.List;

@Data
public class ReturnsOutputDTO {

    private double transactionsTotalAmount;
    private double transactionsTotalCeiling;
    private List<SavingsByDateDTO> savingsByDates;
}