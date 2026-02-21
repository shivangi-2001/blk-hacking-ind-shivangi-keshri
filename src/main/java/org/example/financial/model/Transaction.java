package org.example.financial.model;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Transaction {
    private LocalDateTime date;
    private double amount;
    private double ceiling;
    private double remanent;

    public Transaction(LocalDateTime date,
                       double amount,
                       double ceiling,
                       double remanent) {
        this.date = date;
        this.amount = amount;
        this.ceiling = ceiling;
        this.remanent = remanent;
    }
}
