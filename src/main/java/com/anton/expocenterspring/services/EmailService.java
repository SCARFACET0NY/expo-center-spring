package com.anton.expocenterspring.services;

import com.anton.expocenterspring.model.Ticket;

import java.util.Map;
import java.util.Properties;

public interface EmailService {
    Properties loadProperties();

    String createEmailText(Map<String, Ticket> cart, double total);
}
