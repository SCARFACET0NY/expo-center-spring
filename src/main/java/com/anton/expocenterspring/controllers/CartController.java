package com.anton.expocenterspring.controllers;

import com.anton.expocenterspring.dto.TicketDto;
import com.anton.expocenterspring.model.Exposition;
import com.anton.expocenterspring.model.Ticket;
import com.anton.expocenterspring.services.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CartController {
    private final TicketService ticketService;

    public CartController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/cart")
    public String getCartPage(Model model) {
        return "cart";
    }

    @PostMapping("/addTicket")
    public String addTicket(@RequestParam("exposition_id") String id, HttpSession session) {
        Map<String, TicketDto> cart = (Map<String, TicketDto>) session.getAttribute("cart");
        if (cart == null) cart = new HashMap<>();

        if (id != null) {
            if (!cart.containsKey(id)) {
                cart.put(id, ticketService.createTicketDto(Long.parseLong(id)));
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
        Map<String, TicketDto> cart = (Map<String, TicketDto>) session.getAttribute("cart");
        if (cart.containsKey(id)) {
            cart.get(id).getTicket().setDate(LocalDate.parse(ticketDate));
        }

        session.setAttribute("cart", cart);

        return "redirect:cart";
    }

    @PostMapping("/setQuantity")
    public String setTicketQuantity(@RequestParam("exposition_id") String id, @RequestParam String sign,
                                    HttpSession session) {
        Map<String, TicketDto> cart = (Map<String, TicketDto>) session.getAttribute("cart");
        if (cart.containsKey(id)) {
            Ticket ticket = cart.get(id).getTicket();
            Exposition exposition = cart.get(id).getExposition();
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
}
