package com.neon.controller;

import com.neon.model.Listing;
import com.neon.model.User;
import com.neon.service.ConditionService;
import com.neon.service.ListingService;
import com.neon.service.ModelService;
import com.neon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users/{id}")
public class ListingController {
    @Autowired
    private ListingService listingService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/new/")
    public String createListing(@PathVariable("id") Integer id, @ModelAttribute Listing listing, Model model){
        User user = userService.findOne(id).get();
        model.addAttribute("user", user);
        model.addAttribute("models", ModelService.getAll());
        model.addAttribute("conditions", ConditionService.getAll());
        return "/listing/form";
    }

    @PostMapping
    public String Create(@Valid @ModelAttribute Listing entityListing, @PathVariable("id") Integer id, BindingResult result, RedirectAttributes redirectAttributes){
        Listing listing = null;
        String pagina_retorno = "redirect:/users/";
        listing = listingService.save(entityListing);
        listing.setSellerId(id);
        pagina_retorno =  pagina_retorno + id + "/listing/" + listing.getId();
        return pagina_retorno;
    }

    @GetMapping("/listing/{id}")
    public String showListing(Model model, @PathVariable("id") Integer id){
        if (id != null) {
            Listing listing = ListingService.findOne(id);
            if (listing != null){
                User seller = userService.findOne(listing.getSellerId()).get();
                model.addAttribute("user", seller);
                model.addAttribute("listing", listing);
                return "/listing/show";
            }
        }
        return "/";
    }
}
