package com.neon.controller;

import com.neon.model.Brand;
import com.neon.model.User;
import com.neon.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    private BrandService brandService;

    @RequestMapping("/")
    public String index(Model model){
        Iterable<Brand> brands = brandService.getAll();
        model.addAttribute("brands", brands);
        return "index";
    }

    @GetMapping("/login")
    public String loginIndex(@ModelAttribute User user) {
        return "login/index";
    }

    @PostMapping("/login")
    public String loginSubmit(@Valid @ModelAttribute User user) {
//        TODO
        return "redirect:/";
    }

    @PostMapping("/search")
    public String searchModel(@RequestParam("deviceBrandInput") String deviceBrandId, @RequestParam("deviceModelSearchInput") String deviceModel) {
        if (deviceModel.equals("")) {
            return "redirect:listings/search?brand=" + deviceBrandId;
        } else {
            return "redirect:listings/search?brand=" + deviceBrandId + "&model=" + deviceModel;
        }
    }
}
