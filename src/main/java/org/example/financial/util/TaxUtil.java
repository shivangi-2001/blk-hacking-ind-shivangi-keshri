package org.example.financial.util;

public class TaxUtil {

    public static double calculateTax(double income) {

        if (income <= 700000) {
            return 0;
        }

        if (income <= 1000000) {
            return (income - 700000) * 0.10;
        }

        if (income <= 1200000) {
            return 30000 + (income - 1000000) * 0.15;
        }

        if (income <= 1500000) {
            return 60000 + (income - 1200000) * 0.20;
        }

        return 120000 + (income - 1500000) * 0.30;
    }
}