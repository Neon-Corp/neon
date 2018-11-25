package com.neon.controller;

import com.neon.model.Listing;
import com.neon.model.User;
import com.neon.security.SecurityUtils;
import com.neon.service.BrandService;
import com.neon.service.ConditionService;
import com.neon.service.ListingService;
import com.neon.service.ModelService;
import com.neon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/listings")
public class ListingController {
    @Autowired
    private ListingService listingService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private ModelService modelService;
    @Autowired
    private ConditionService conditionService;
    @Autowired
    private UserService userService;

    @GetMapping
    public String index(Model model) {
        Iterable<Listing> allListings = listingService.findAll();
        model.addAttribute("allListings", allListings);
        return "listings/index";
    }

    @GetMapping(value = "/new")
    public String createListing(@ModelAttribute Listing listing, Model model){
        String loggedInUsername = SecurityUtils.getLoggedInUsername();
        User user = userService.findOneByUsername(loggedInUsername);
        model.addAttribute("user", user);
        model.addAttribute("models", modelService.getAll());
        model.addAttribute("conditions", conditionService.getAll());
        return "/listing/form";
    }

    @PostMapping
    public String Create(@ModelAttribute Listing entityListing){
        Listing listing = listingService.save(entityListing);
        String pagina_retorno = "redirect:/listings/" + listing.getId();
        return pagina_retorno;
    }

    @GetMapping("/{id}")
    public String showListing(Model model, @PathVariable("id") Integer id){
        Listing listing = listingService.findOne(id).get();
        User seller = userService.findOne(listing.getSellerId()).get();
        model.addAttribute("user", seller);
        model.addAttribute("listing", listing);
        return "/listing/show";
    }

    @GetMapping("/search")
    public String brandModelsResult(@RequestParam("brand") Integer brandID, Model model) {
        List<Listing> listingsFromBrand = new ArrayList<>();

        Iterable<com.neon.model.Model> modelsFromBrand = modelService.getAllFromBrand(brandID);
        for (com.neon.model.Model m : modelsFromBrand){
            Iterable<Listing> modelListings = listingService.findAllFromModel(m.getId());
            for (Listing l : modelListings){
                listingsFromBrand.add(l);
            }
        }
        model.addAttribute("brandListings", listingsFromBrand);
        return "listings/index";
    }

    @GetMapping("/{id}/delete")
    public String deleteListing(Model model, @PathVariable("id") Integer listingId) {
        Listing listing = listingService.findOne(listingId).get();
        Integer sellerId = listing.getSellerId();
        String loggedInUsername = SecurityUtils.getLoggedInUsername();
        User user = userService.findOneByUsername(loggedInUsername);
        if (sellerId == user.getId()) {
            listingService.delete(listing);
        }
        return "redirect:/users/my-account";
    }
}
