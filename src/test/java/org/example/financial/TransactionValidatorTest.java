package org.example.financial;

import org.example.financial.dto.TransactionDTO;
import org.example.financial.dto.ValidateInputDTO;
import org.example.financial.dto.ValidationResponseDTO;
import org.example.financial.service.TransactionValidatorService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionValidatorTest {
    private final TransactionValidatorService validator =
            new TransactionValidatorService();

    @Test
    void testNegativeAmountInvalid() {

        TransactionDTO txn = new TransactionDTO();
        txn.setDate("2023-01-01 10:00:00");
        txn.setAmount(-100);

        ValidateInputDTO request = new ValidateInputDTO();
        request.setWage(50000);
        request.setTransactions(List.of(txn));

        ValidationResponseDTO result =
                validator.validateTransactions(request);

        assertEquals(1, result.getInvalid().size());
    }
}
