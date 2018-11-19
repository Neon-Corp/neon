package com.neon.controller;

import com.neon.model.Brand;
import com.neon.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/teste")
    public String searchModel(@RequestParam("deviceBrandInput") String deviceBrand, @RequestParam("deviceModelSearchInput") String deviceModel) {
        return "redirect:listings/search?brand=" + deviceBrand + "&model=" + deviceModel;
    }
}
