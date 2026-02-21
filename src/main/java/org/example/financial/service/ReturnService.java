package org.example.financial.service;

import org.example.financial.dto.*;
import org.example.financial.util.DateUtil;
import org.example.financial.util.TaxUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReturnService {

    public ReturnsOutputDTO calculateReturns(
            ReturnsRequestDTO request,
            double rate,
            boolean isNPS) {

        int years =
                request.getAge() >= 60 ?
                        5 : 60 - request.getAge();

        double annualIncome = request.getWage() * 12;

        double totalAmount = 0;
        double totalCeiling = 0;

        List<TransactionDTO> transactions =
                request.getTransactions();

        // 🔵 First apply Q and P rules
        for (TransactionDTO txn : transactions) {

            totalAmount += txn.getAmount();
            totalCeiling += txn.getCeiling();

            LocalDateTime txnDate =
                    DateUtil.parse(txn.getDate());

            // Q rule (latest start wins)
            QPeriodDTO selectedQ = null;

            for (QPeriodDTO q : request.getQ()) {

                LocalDateTime start =
                        DateUtil.parse(q.getStart());
                LocalDateTime end =
                        DateUtil.parse(q.getEnd());

                if (DateUtil.isInRange(txnDate, start, end)) {

                    if (selectedQ == null ||
                            DateUtil.parse(q.getStart())
                                    .isAfter(DateUtil.parse(selectedQ.getStart()))) {

                        selectedQ = q;
                    }
                }
            }

            if (selectedQ != null) {
                txn.setRemanent(selectedQ.getFixed());
            }

            // P rule (add all)
            for (PPeriodDTO p : request.getP()) {

                LocalDateTime start =
                        DateUtil.parse(p.getStart());
                LocalDateTime end =
                        DateUtil.parse(p.getEnd());

                if (DateUtil.isInRange(txnDate, start, end)) {
                    txn.setRemanent(
                            txn.getRemanent() + p.getExtra());
                }
            }
        }

        // 🟡 Now process each K period
        List<SavingsByDateDTO> savingsList =
                new ArrayList<>();

        for (KPeriodDTO k : request.getK()) {

            LocalDateTime start =
                    DateUtil.parse(k.getStart());
            LocalDateTime end =
                    DateUtil.parse(k.getEnd());

            double sum = 0;

            for (TransactionDTO txn : transactions) {

                LocalDateTime txnDate =
                        DateUtil.parse(txn.getDate());

                if (DateUtil.isInRange(txnDate, start, end)) {
                    sum += txn.getRemanent();
                }
            }

            double futureValue =
                    sum * Math.pow(1 + rate, years);

            double realValue =
                    futureValue /
                            Math.pow(1 + request.getInflation() / 100, years);

            double profits = realValue - sum;

            double taxBenefit = 0;

            if (isNPS) {

                double deduction =
                        Math.min(sum,
                                Math.min(annualIncome * 0.10, 200000));

                taxBenefit =
                        TaxUtil.calculateTax(annualIncome)
                                - TaxUtil.calculateTax(
                                annualIncome - deduction);
            }

            SavingsByDateDTO dto =
                    new SavingsByDateDTO();

            dto.setStart(k.getStart());
            dto.setEnd(k.getEnd());
            dto.setAmount(sum);
            dto.setProfits(profits);
            dto.setTaxBenefit(taxBenefit);

            savingsList.add(dto);
        }

        ReturnsOutputDTO output =
                new ReturnsOutputDTO();

        output.setTransactionsTotalAmount(totalAmount);
        output.setTransactionsTotalCeiling(totalCeiling);
        output.setSavingsByDates(savingsList);

        return output;
    }
}