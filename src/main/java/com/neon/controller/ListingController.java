package com.neon.controller;

import com.neon.model.*;
import com.neon.service.*;
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
    @Autowired
    private OrderService orderService;

    @GetMapping
    public String index(Model model) {
        if (SecurityService.isUserLoggedIn()) {
            model.addAttribute("loggedInUsername", SecurityService.getLoggedInUsername());
        }
        Iterable<Listing> allListings = listingService.findAll();
        model.addAttribute("allListings", allListings);
        return "listings/index";
    }

    @GetMapping(value = "/new")
    public String createListing(@ModelAttribute Listing listing, Model model){
        String loggedInUsername = SecurityService.getLoggedInUsername();
        User user = userService.findOneByUsername(loggedInUsername);
        model.addAttribute("user", user);
        model.addAttribute("conditions", conditionService.getAll());
        return "/listing/form";
    }

    @PostMapping
    public String Create(@ModelAttribute Listing entityListing){
        entityListing.setStatus(1);
        Listing listing = listingService.save(entityListing);
        return "redirect:/listings/" + listing.getId();
    }

    @GetMapping("/{id}")
    public String showListing(Model model, @PathVariable("id") Integer id){
        Listing listing = listingService.findOne(id).get();
        User seller = userService.findOne(listing.getSellerId()).get();

        String m = modelService.getNameById(listing.getModel().getId());
        model.addAttribute("model", m);

        model.addAttribute("user", seller);
        model.addAttribute("listing", listing);

        String userName = SecurityService.getLoggedInUsername();

        boolean sameUser = (userName.equals(seller.getUsername()));
        model.addAttribute("logged", sameUser);
        return "/listing/show";
    }

    @GetMapping("/search")
    public String brandModelsResult(@RequestParam("brand") Integer brandID, @RequestParam("model") String modelName, Model model) {
        if (SecurityService.isUserLoggedIn()) {
            model.addAttribute("loggedInUsername", SecurityService.getLoggedInUsername());
        }
        String brandName = brandService.getBrandById(brandID).getBrand();
        model.addAttribute("brandName", brandName);
        if (modelName.isEmpty()) {
            List<Listing> listingsFromBrand = new ArrayList<>();
            Iterable<com.neon.model.Model> modelsFromBrand = modelService.getAllFromBrand(brandID);
            for (com.neon.model.Model m : modelsFromBrand){
                Iterable<Listing> modelListings = listingService.findAllFromModel(m.getId());
                for (Listing l : modelListings){
                    listingsFromBrand.add(l);
                }
            }
            model.addAttribute("listings", listingsFromBrand);
            return "listings/index";
        } else {
            Integer searchedModelId = modelService.getIdByName(modelName);
            List<Listing> listingsFromBrand = new ArrayList<>();
            Iterable<com.neon.model.Model> modelsFromBrand = modelService.getAllFromBrand(brandID);
            for (com.neon.model.Model m : modelsFromBrand){
                Iterable<Listing> modelListings = listingService.findAllFromModel(m.getId());
                for (Listing l : modelListings){
                    if (l.getModel().getId().equals(searchedModelId))
                        listingsFromBrand.add(l);
                }
            }
            model.addAttribute("listings", listingsFromBrand);
            return "listings/index";
        }
    }

    @GetMapping("/{id}/edit")
    public String editListing(Model model, @PathVariable("id") Integer listingId) {
        String loggedInUsername = SecurityService.getLoggedInUsername();
        User user = userService.findOneByUsername(loggedInUsername);
        model.addAttribute("user", user);
        Listing listing = listingService.findOne(listingId).get();
        model.addAttribute("listing", listing);
        com.neon.model.Model deviceModel = modelService.findOneById(listing.getModel().getId());
        model.addAttribute("deviceModel", deviceModel);
        Brand deviceBrand = brandService.getBrandById(deviceModel.getBrandId());
        model.addAttribute("deviceBrand", deviceBrand);
        model.addAttribute("conditions", conditionService.getAll());
        Condition condition = conditionService.findOnyById(listing.getConditionId());
        model.addAttribute("deviceCondition", condition);
        return "listing/form";
    }

    @PutMapping
    public String editListing(@ModelAttribute Listing entityListing) {
        listingService.save(entityListing);
        return "redirect:/listings/" + entityListing.getId();
    }

    @GetMapping("/{id}/delete")
    public String deleteListing(Model model, @PathVariable("id") Integer listingId) {
        Listing listing = listingService.findOne(listingId).get();
        Integer sellerId = listing.getSellerId();
        String loggedInUsername = SecurityService.getLoggedInUsername();
        User user = userService.findOneByUsername(loggedInUsername);
        if (sellerId.equals(user.getId())) {
            listingService.delete(listing);
        }
        return "redirect:/users/my-account";
    }

    @GetMapping("/{id}/buy")
    public String buy(@PathVariable("id") Integer listingId, Model model){
        String loggedInUsername = SecurityService.getLoggedInUsername();
        User user = userService.findOneByUsername(loggedInUsername);
        orderService.saveOrder(listingId, user);
        return "redirect:/users/my-account";
    }
}
