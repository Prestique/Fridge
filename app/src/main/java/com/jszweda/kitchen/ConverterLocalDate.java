package com.jszweda.kitchen;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import androidx.room.TypeConverter;

class ConverterLocalDate {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    @TypeConverter
    public static LocalDate toLocalDate(String value) {
        return value == null ? null : LocalDate.parse(value, formatter);
    }

    @TypeConverter
    public static String fromLocalDate(LocalDate date) {
        return date == null ? null : date.format(formatter);
    }
}
