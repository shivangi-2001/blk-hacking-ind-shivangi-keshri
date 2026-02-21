package org.example.financial.dto;

import lombok.Data;

@Data
public class ValidTransactionDTO extends TransactionDTO{
    private boolean inKPeriod;
}
