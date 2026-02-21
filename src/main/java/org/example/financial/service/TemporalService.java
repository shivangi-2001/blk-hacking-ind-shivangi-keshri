package org.example.financial.service;

import org.example.financial.dto.*;
import org.example.financial.util.DateUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TemporalService {

    public TemporalConstraintOutput applyTemporalRules(
            TemporalConstraintInput request) {

        List<TransactionDTO> transactions = request.getTransaction();
        List<QPeriodDTO> qPeriods = request.getQ();
        List<KPeriodDTO> kPeriods = request.getK();
        List<PPeriodDTO> pPeriods = request.getP();

        List<ValidTransactionDTO> validList = new ArrayList<>();
        List<InvalidTransactionDTO> invalidList = new ArrayList<>();

        for (TransactionDTO txn : transactions) {

            try {

                LocalDateTime txnDate =
                        DateUtil.parse(txn.getDate());

                // 🔵 Apply Q rule (latest start wins)
                QPeriodDTO selectedQ = null;

                for (QPeriodDTO q : qPeriods) {

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

                // 🟢 Apply P rule (add all extras)
                for (PPeriodDTO p : pPeriods) {

                    LocalDateTime start =
                            DateUtil.parse(p.getStart());
                    LocalDateTime end =
                            DateUtil.parse(p.getEnd());

                    if (DateUtil.isInRange(txnDate, start, end)) {
                        txn.setRemanent(
                                txn.getRemanent() + p.getExtra());
                    }
                }

                // 🟡 Check if transaction is inside any K period
                boolean inKPeriod = false;

                for (KPeriodDTO k : kPeriods) {

                    LocalDateTime start =
                            DateUtil.parse(k.getStart());
                    LocalDateTime end =
                            DateUtil.parse(k.getEnd());

                    if (DateUtil.isInRange(txnDate, start, end)) {
                        inKPeriod = true;
                        break;
                    }
                }

                // Build valid transaction output
                ValidTransactionDTO valid =
                        new ValidTransactionDTO();

                valid.setDate(txn.getDate());
                valid.setAmount(txn.getAmount());
                valid.setCeiling(txn.getCeiling());
                valid.setRemanent(txn.getRemanent());
                valid.setInKPeriod(inKPeriod);

                validList.add(valid);

            } catch (Exception e) {

                invalidList.add(
                        InvalidTransactionDTO.create(txn,
                                "Invalid date format"));
            }
        }

        // Build final output
        TemporalConstraintOutput output =
                new TemporalConstraintOutput();

        output.setValid(validList);
        output.setInvalid(invalidList);

        return output;
    }
}