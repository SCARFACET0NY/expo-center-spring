package com.anton.expocenterspring.controllers;

import com.anton.expocenterspring.auth.UserPrincipal;
import com.anton.expocenterspring.model.Exposition;
import com.anton.expocenterspring.services.ExpositionService;
import com.anton.expocenterspring.services.HallService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AdminController {
    private final HallService hallService;
    private final ExpositionService expositionService;

    public AdminController(HallService hallService, ExpositionService expositionService) {
        this.hallService = hallService;
        this.expositionService = expositionService;
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAdminPage(Model model) {
        model.addAttribute("user",
                ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser());

        return "admin";
    }

    @GetMapping("/admin/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddExpositionPage(Model model) {
        model.addAttribute("exposition", new Exposition());
        model.addAttribute("halls", hallService.getAllHalls());

        return "add-exposition";
    }

    @PostMapping("/admin/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addExposition(@ModelAttribute Exposition exposition) {
        expositionService.save(exposition);
        return "redirect:/admin";
    }
}
