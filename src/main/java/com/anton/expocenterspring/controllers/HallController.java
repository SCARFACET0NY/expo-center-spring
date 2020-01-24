package com.anton.expocenterspring.controllers;

import com.anton.expocenterspring.enums.HallType;
import com.anton.expocenterspring.services.HallService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HallController {
    private final HallService hallService;

    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @RequestMapping({"", "/"})
    public String getAllHalls(Model model) {
        model.addAttribute("halls", hallService.getAllHalls());
        return "index";
    }

    @RequestMapping("/largeHall")
    public String getLargeHall(Model model) {
        model.addAttribute("hall", hallService.getHallByType(HallType.LARGE));
        return "hall";
    }

    @RequestMapping("/mediumHall")
    public String getMediumHall(Model model) {
        model.addAttribute("hall", hallService.getHallByType(HallType.MEDIUM));
        return "hall";
    }

    @RequestMapping("/smallHall")
    public String getSmallHall(Model model) {
        model.addAttribute("hall", hallService.getHallByType(HallType.SMALL));
        return "hall";
    }
}
