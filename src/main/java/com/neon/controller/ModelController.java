package com.neon.controller;

import com.neon.service.SecurityService;
import com.neon.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/models")
public class ModelController {
    @Autowired
    private ModelService modelService;

    @GetMapping("/search")
    public String search(org.springframework.ui.Model model){
        if (SecurityService.isUserLoggedIn()) {
            model.addAttribute("loggedInUsername", SecurityService.getLoggedInUsername());
        }
        return "index";
    }
}
