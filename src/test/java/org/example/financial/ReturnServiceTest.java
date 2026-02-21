package org.example.financial;

import org.example.financial.dto.KPeriodDTO;
import org.example.financial.dto.ReturnsOutputDTO;
import org.example.financial.dto.ReturnsRequestDTO;
import org.example.financial.dto.TransactionDTO;
import org.example.financial.service.ReturnService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ReturnServiceTest {
    private final ReturnService service = new ReturnService();

    @Test
    void testBasicReturnCalculation(){

        TransactionDTO txn = new TransactionDTO();

        txn.setDate("2023-01-01 10:00:00");
        txn.setAmount(100);
        txn.setCeiling(100);
        txn.setRemanent(10);

        ReturnsRequestDTO request = new ReturnsRequestDTO();

        request.setAge(30);
        request.setWage(50000);
        request.setInflation(5.0);
        request.setTransactions(List.of(txn));

        KPeriodDTO k = new KPeriodDTO();

        k.setStart("2023-01-01 00:00:00");
        k.setEnd("2023-12-31 23:59:59");

        request.setK(List.of(k));

        ReturnsOutputDTO res = service.calculateReturns(request, 0.0711, true);

        assertNotNull(res);
        assertTrue(res.getTransactionsTotalAmount()>0);
        assertEquals(1, res.getSavingsByDates().size());
    }
}
