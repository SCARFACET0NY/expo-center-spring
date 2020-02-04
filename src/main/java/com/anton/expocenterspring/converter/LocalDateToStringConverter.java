package com.anton.expocenterspring.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

public class LocalDateToStringConverter implements Converter<LocalDate, String> {
    @Override
    public String convert(LocalDate date) {
        return date.toString();
    }
}
