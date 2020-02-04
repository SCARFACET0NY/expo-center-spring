package com.anton.expocenterspring.services.impl;

import com.anton.expocenterspring.auth.UserPrincipal;
import com.anton.expocenterspring.model.Ticket;
import com.anton.expocenterspring.model.User;
import com.anton.expocenterspring.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {
    public Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = EmailServiceImpl.class.getClassLoader().getResourceAsStream("mail.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public String createEmailText(Map<String, Ticket> cart, double total) {
        User user = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        String cardNumber = String.valueOf(user.getCardNumber());
        String lastFourDigits = cardNumber.substring(cardNumber.length() - 4);

        StringBuilder message = new StringBuilder("<h2>Tickets</h2>");
        for (Ticket ticket : cart.values()) {
            message.append("<p>title: " + ticket.getExposition().getTitle() + ", ");
            message.append("date: " + ticket.getDate() + ", ");
            message.append("quantity: " + ticket.getQuantity() + ", ");
            message.append("price: " + ticket.getExposition().getPrice() + "</p>");
        }
        message.append("<br/>");
        message.append("<p>buyer: " + user.getFirstName() + " " + user.getLastName() + ", ");
        message.append("card: ******" + lastFourDigits + ", total: " + total + "</p>");

        return message.toString();
    }
}
