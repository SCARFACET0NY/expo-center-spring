package com.anton.expocenterspring.controllers;

import com.anton.expocenterspring.dto.TicketDto;
import com.anton.expocenterspring.services.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
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
}
