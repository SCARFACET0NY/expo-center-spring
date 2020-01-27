package com.anton.expocenterspring.controllers;

import com.anton.expocenterspring.dto.TicketDto;
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
    @GetMapping("/cart")
    public String getCartPage(Model model) {
        return "cart";
    }

    @PostMapping("/addTicket")
    public String addTicket(@RequestParam("exposition_id") String id, HttpSession session) {
        Map<String, TicketDto> cart = (Map<String, TicketDto>) session.getAttribute("cart");
        if (cart == null) cart = new HashMap<>();

        return "redirect:" + session.getAttribute("origin");
    }
}
