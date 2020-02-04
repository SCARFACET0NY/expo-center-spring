package com.anton.expocenterspring.controllers;

import com.anton.expocenterspring.auth.UserPrincipal;
import com.anton.expocenterspring.model.Exposition;
import com.anton.expocenterspring.model.Payment;
import com.anton.expocenterspring.model.Ticket;
import com.anton.expocenterspring.model.User;
import com.anton.expocenterspring.services.EmailService;
import com.anton.expocenterspring.services.PaymentService;
import com.anton.expocenterspring.services.TicketService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Controller
public class CartController {
    private final TicketService ticketService;
    private final PaymentService paymentService;
    private final EmailService emailService;

    public CartController(TicketService ticketService, PaymentService paymentService, EmailService emailService) {
        this.ticketService = ticketService;
        this.paymentService = paymentService;
        this.emailService = emailService;
    }

    @GetMapping("/cart")
    public String getCartPage(Model model) {
        return "cart";
    }

    @PostMapping("/addTicket")
    public String addTicket(@RequestParam("exposition_id") String id, HttpSession session) {
        Map<String, Ticket> cart = (Map<String, Ticket>) session.getAttribute("cart");
        if (cart == null) cart = new HashMap<>();

        if (id != null) {
            if (!cart.containsKey(id)) {
                cart.put(id, ticketService.createTicket(Long.parseLong(id)));
            }
        }
        double total = ticketService.getCartTotal(cart);

        session.setAttribute("cart", cart);
        session.setAttribute("total", total);

        return "redirect:" + session.getAttribute("origin");
    }

    @PostMapping("/setDate")
    public String setTicketDate(@RequestParam("exposition_id") String id, @RequestParam("ticket_date") String ticketDate,
                                HttpSession session) {
        Map<String, Ticket> cart = (Map<String, Ticket>) session.getAttribute("cart");
        if (cart.containsKey(id)) {
            cart.get(id).setDate(LocalDate.parse(ticketDate));
        }

        session.setAttribute("cart", cart);

        return "redirect:cart";
    }

    @PostMapping("/setQuantity")
    public String setTicketQuantity(@RequestParam("exposition_id") String id, @RequestParam String sign,
                                    HttpSession session) {
        Map<String, Ticket> cart = (Map<String, Ticket>) session.getAttribute("cart");
        if (cart.containsKey(id)) {
            Ticket ticket = cart.get(id);
            Exposition exposition = ticket.getExposition();
            if (sign.equals("+")) {
                ticket.setQuantity(ticket.getQuantity() + 1);
                session.setAttribute("total", (double) session.getAttribute("total") + exposition.getPrice());
            } else if (sign.equals("-")) {
                if (ticket.getQuantity() > 1) {
                    ticket.setQuantity(ticket.getQuantity() - 1);
                    session.setAttribute("total", (double) session.getAttribute("total") - exposition.getPrice());
                }
            }
        }

        return "redirect:cart";
    }

    @PostMapping("/removeTicket")
    public String removeTicket(@RequestParam("exposition_id") String id, HttpSession session) {
        Map<String, Ticket> cart = (Map<String, Ticket>) session.getAttribute("cart");

        cart.keySet().removeIf(expositionId -> expositionId.equals(id));
        double total = ticketService.getCartTotal(cart);

        session.setAttribute("cart", cart);
        session.setAttribute("total", total);

        return "redirect:cart";
    }

    @PostMapping("/pay")
    public String payCart(HttpSession session) {
        Map<String, Ticket> cart = (Map<String, Ticket>) session.getAttribute("cart");

        if (!cart.isEmpty()) {
            double total = ticketService.getCartTotal(cart);
            Payment payment = paymentService.savePayment(total);

            cart.values().forEach(ticket -> {
                ticketService.saveTicket(ticket, payment);
            });

            return "redirect:sendMail";
        }

        return "redirect:";
    }

    @GetMapping("/sendMail")
    public String sendMail(HttpSession session) {
        User user = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        Map<String, Ticket> cart = (Map<String, Ticket>) session.getAttribute("cart");
        double total = (double) session.getAttribute("total");

        Properties properties = emailService.loadProperties();
        Session mailSession = Session.getDefaultInstance(properties);

        try {
            MimeMessage message = new MimeMessage(mailSession);

            message.setFrom(new InternetAddress(properties.getProperty("mail.smtp.host")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
            message.setSubject("Tickets from Expo Center");
            message.setContent(emailService.createEmailText(cart, total), "text/html");

            Transport transport = mailSession.getTransport("smtp");
            transport.connect(properties.getProperty("mail.smtp.host"), properties.getProperty("mail.smtp.user"),
                    properties.getProperty("mail.smtp.password"));
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        cart.clear();
        session.setAttribute("cart", null);
        session.setAttribute("total", null);

        return "redirect:";
    }
}
