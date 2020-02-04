package com.anton.expocenterspring.controllers;

import com.anton.expocenterspring.auth.UserPrincipal;
import com.anton.expocenterspring.model.Payment;
import com.anton.expocenterspring.model.User;
import com.anton.expocenterspring.services.PaymentService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaymentHistoryController {
    private final PaymentService paymentService;

    public PaymentHistoryController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/paymentHistory")
    public String getPaymentHistoryPage(@RequestParam(defaultValue = "0", name = "page") int page, Model model) {
        User user = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        Page<Payment> paymentsPage = paymentService.getPaymentsPageForUser(user, page);

        model.addAttribute("payments", paymentsPage.getContent());
        model.addAttribute("numOfPages", paymentsPage.getTotalPages());
        model.addAttribute("page", page);

        return "payment-history";
    }
}
