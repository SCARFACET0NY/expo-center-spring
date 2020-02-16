package com.anton.expocenterspring.controllers;

import com.anton.expocenterspring.enums.HallType;
import com.anton.expocenterspring.model.Hall;
import com.anton.expocenterspring.services.ExpositionService;
import com.anton.expocenterspring.services.HallService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HallController {
    private final HallService hallService;
    private final ExpositionService expositionService;

    public HallController(HallService hallService, ExpositionService expositionService) {
        this.hallService = hallService;
        this.expositionService = expositionService;
    }

    @RequestMapping({"", "/"})
    public String getAllHalls(HttpSession session, Model model) {
        model.addAttribute("halls", hallService.getAllHalls());
        if (session.getAttribute("mailSuccess") != null) {
            model.addAttribute("mailSuccess", session.getAttribute("mailSuccess"));
            session.setAttribute("mailSuccess", null);
        }

        return "index";
    }

    @RequestMapping("/largeHall")
    public String getLargeHall(Model model, HttpServletRequest request, HttpSession session) {
        Hall hall = hallService.getHallByType(HallType.LARGE);
        model.addAttribute("hall", hall);
        model.addAttribute("expositions", expositionService.getActiveExpositionsForHall(hall));
        session.setAttribute("origin", request.getRequestURI().substring(1));

        return "hall";
    }

    @RequestMapping("/mediumHall")
    public String getMediumHall(Model model, HttpServletRequest request, HttpSession session) {
        Hall hall = hallService.getHallByType(HallType.MEDIUM);
        model.addAttribute("hall", hall);
        model.addAttribute("expositions", expositionService.getActiveExpositionsForHall(hall));
        session.setAttribute("origin", request.getRequestURI().substring(1));

        return "hall";
    }

    @RequestMapping("/smallHall")
    public String getSmallHall(Model model, HttpServletRequest request, HttpSession session) {
        Hall hall = hallService.getHallByType(HallType.SMALL);
        model.addAttribute("hall", hall);
        model.addAttribute("expositions", expositionService.getActiveExpositionsForHall(hall));
        session.setAttribute("origin", request.getRequestURI().substring(1));

        return "hall";
    }
}
