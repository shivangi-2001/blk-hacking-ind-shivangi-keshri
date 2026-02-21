package org.example.financial.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private  static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LocalDateTime parse(String date){
        return LocalDateTime.parse(date, FORMATTER);
    }

    public static String format(LocalDateTime date){
        return date.format(FORMATTER);
    }

    public static boolean isInRange(
            LocalDateTime txn,
            LocalDateTime start,
            LocalDateTime end) {

        return !txn.isBefore(start) && !txn.isAfter(end);
    }
}
