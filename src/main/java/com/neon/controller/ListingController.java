package com.neon.controller;

import com.neon.model.Listing;
import com.neon.service.ConditionService;
import com.neon.service.ListingService;
import com.neon.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/listings")
public class ListingController {
    @Autowired
    private ListingService listingService;

    @GetMapping(value = "/new")
    public String createListing(@ModelAttribute Listing listing, Model model){
        model.addAttribute("models", ModelService.getAll());
        model.addAttribute("conditions", ConditionService.getAll());
        return "/listing/form";
    }

    @PostMapping
    public String Create(@Valid @ModelAttribute Listing entityListing, BindingResult result, RedirectAttributes redirectAttributes){
        Listing listing = null;
        String pagina_retorno = "redirect:/listings/";
        listing = listingService.save(entityListing);
        pagina_retorno =  pagina_retorno + listing.getId();
        return pagina_retorno;
    }

    @GetMapping("/{id}")
    public String showListing(Model model, @PathVariable("id") Integer id){
        if (id != null) {
            Listing listing = ListingService.findOne(id);
            if (listing != null){
                model.addAttribute("listing", listing);
                return "/listing/show";
            }
        }
        return "/";
    }
}
