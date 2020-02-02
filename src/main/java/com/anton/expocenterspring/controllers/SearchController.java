package com.anton.expocenterspring.controllers;

import com.anton.expocenterspring.model.Exposition;
import com.anton.expocenterspring.services.ExpositionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
public class SearchController {
    private final ExpositionService expositionService;

    public SearchController(ExpositionService expositionService) {
        this.expositionService = expositionService;
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false, name = "query") String query, Model model) {
        if (query != null) {
            Set<Exposition> expositions = expositionService.searchExpositionsByTitle(query);
            if (!expositions.isEmpty()) {
                model.addAttribute("expositions", expositions);
            } else {
                model.addAttribute("emptySearch", "No Expositions Found");
            }
        }

        return "search";
    }
}
