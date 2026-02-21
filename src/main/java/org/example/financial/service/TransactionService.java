package org.example.financial.service;

import org.example.financial.dto.ExpenseDTO;
import org.example.financial.dto.TransactionDTO;
import org.example.financial.model.Transaction;
import org.example.financial.util.DateUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    public List<TransactionDTO> parseTransaction(List<ExpenseDTO> expenses){
        List<TransactionDTO> responseList = new ArrayList<>();

        for(ExpenseDTO expense: expenses){
            LocalDateTime date = DateUtil.parse(expense.getDate());
            double ceiling = Math.ceil(expense.getAmount()/100.0)*100;
            double remanent = ceiling - expense.getAmount();

            Transaction trans = new Transaction(date, expense.getAmount(), ceiling, remanent);

            TransactionDTO dto = new TransactionDTO();

            dto.setDate(DateUtil.format(trans.getDate()));
            dto.setAmount(trans.getAmount());
            dto.setCeiling(trans.getCeiling());
            dto.setRemanent(trans.getRemanent());

            responseList.add(dto);
        }

        return responseList;
    }
}
