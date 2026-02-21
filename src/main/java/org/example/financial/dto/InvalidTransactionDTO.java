package org.example.financial.dto;

import lombok.Data;

@Data
public class InvalidTransactionDTO extends TransactionDTO{
    private String message;

    public static InvalidTransactionDTO create(
            TransactionDTO txn,
            String message) {

        InvalidTransactionDTO invalid = new InvalidTransactionDTO();

        invalid.setDate(txn.getDate());
        invalid.setAmount(txn.getAmount());
        invalid.setCeiling(txn.getCeiling());
        invalid.setRemanent(txn.getRemanent());
        invalid.setMessage(message);

        return invalid;
    }
}
