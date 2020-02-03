package com.anton.expocenterspring.controllers;

import com.anton.expocenterspring.auth.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/admin")
    public String getAdminPage(Model model) {
        model.addAttribute("user",
                ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser());

        return "admin";
    }
}
