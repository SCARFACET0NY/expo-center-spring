package com.anton.expocenterspring.controllers;

import com.anton.expocenterspring.enums.HallType;
import com.anton.expocenterspring.model.Hall;
import com.anton.expocenterspring.services.ExpositionService;
import com.anton.expocenterspring.services.HallService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
public class HallController {
    private final HallService hallService;
    private final ExpositionService expositionService;

    public HallController(HallService hallService, ExpositionService expositionService) {
        this.hallService = hallService;
        this.expositionService = expositionService;
    }

    @RequestMapping({"", "/"})
    public String getAllHalls(Model model) {
        model.addAttribute("halls", hallService.getAllHalls());
        return "index";
    }

    @RequestMapping("/largeHall")
    public String getLargeHall(Model model) {
        Hall hall = hallService.getHallByType(HallType.LARGE);
        model.addAttribute("hall", hall);
        model.addAttribute("expositions", expositionService.getActiveExpositionsForHall(hall));
        return "hall";
    }

    @RequestMapping("/mediumHall")
    public String getMediumHall(Model model) {
        Hall hall = hallService.getHallByType(HallType.MEDIUM);
        model.addAttribute("hall", hall);
        model.addAttribute("expositions", expositionService.getActiveExpositionsForHall(hall));
        return "hall";
    }

    @RequestMapping("/smallHall")
    public String getSmallHall(Model model) {
        Hall hall = hallService.getHallByType(HallType.SMALL);
        model.addAttribute("hall", hall);
        model.addAttribute("expositions", expositionService.getActiveExpositionsForHall(hall));
        return "hall";
    }
}
