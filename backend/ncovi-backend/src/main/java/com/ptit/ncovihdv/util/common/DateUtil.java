package com.ptit.ncovihdv.util.common;

import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static final String yyyyMMdd = "yyyy-MM-dd";
    public static final String ddMMyyyy = "dd/MM/yyyy";
    public static final String ddMMyyyyHHmmss = "dd-MM-yyyy HH:mm:ss";

    public static final LocalDateTime getCreateAt() {
        return LocalDateTime.now();
    }

    public static LocalDateTime formatStringToLocalDateTime(String time) {
        if (!StringUtils.isEmpty(time)) {
            if (time.trim().matches("\\d{4}-\\d{2}-\\d{2}")) {
                time += " 00:00:00";
            } else {
                return null;
            }
            LocalDateTime dateTime = LocalDateTime.parse(time, DateTimeFormatter.ofPattern(ddMMyyyyHHmmss));
            return dateTime;
        }
        return null;
    }

    public static LocalDate formatStringToLocalDate(String time) {
        if (!StringUtils.isEmpty(time)) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(yyyyMMdd);
            LocalDate localDate = LocalDate.parse(time, dtf);

            return localDate;
        }
        return null;
    }

    public static String formatLocalDateToString(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern(ddMMyyyy));
    }

}
