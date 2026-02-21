package org.example.financial.service;

import org.example.financial.dto.InvalidTransactionDTO;
import org.example.financial.dto.TransactionDTO;
import org.example.financial.dto.ValidateInputDTO;
import org.example.financial.dto.ValidationResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TransactionValidatorService {

    public ValidationResponseDTO validateTransactions(ValidateInputDTO request) {

        double wage = request.getWage();
        List<TransactionDTO> transactions = request.getTransactions();

        Set<String> seenDate = new HashSet<>();

        List<TransactionDTO> validList = new ArrayList<>();
        List<InvalidTransactionDTO> invalidList = new ArrayList<>();

        for (TransactionDTO txn : transactions) {

            // 1️⃣ Negative amount check
            if (txn.getAmount() < 0) {
                invalidList.add(InvalidTransactionDTO.create(txn,
                        "Amount cannot be negative"));
                continue;
            }

            // 2️⃣ Duplicate timestamp check
            if (seenDate.contains(txn.getDate())) {
                invalidList.add(InvalidTransactionDTO.create(txn,
                        "Duplicate transaction timestamp"));
                continue;
            }

            seenDate.add(txn.getDate());

            // 3️⃣ Wage sanity check (optional basic check)
            if (txn.getRemanent() > wage) {
                invalidList.add(InvalidTransactionDTO.create(txn,
                        "Investment exceeds wage"));
                continue;
            }

            validList.add(txn);
        }

        ValidationResponseDTO response = new ValidationResponseDTO();
        response.setValid(validList);
        response.setInvalid(invalidList);

        return response;
    }
}