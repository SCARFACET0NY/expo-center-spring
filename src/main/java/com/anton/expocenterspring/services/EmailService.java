package com.anton.expocenterspring.services;

import com.anton.expocenterspring.dto.TicketDto;

import java.util.Map;
import java.util.Properties;

public interface EmailService {
    Properties loadProperties();

    String createEmailText(Map<String, TicketDto> cart, double total);
}
