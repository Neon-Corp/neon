package com.neon.controller;

import com.neon.model.Listing;
import com.neon.model.User;
import com.neon.service.SecurityService;
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

        String m = modelService.getNameById(listing.getModelId());
        model.addAttribute("model", m);

        model.addAttribute("user", seller);
        model.addAttribute("listing", listing);

        String userName = SecurityService.getLoggedInUsername();

        boolean sameUser = (userName.equals(seller.getUsername()));
        model.addAttribute("logged", sameUser);
        return "/listing/show";
    }

    @GetMapping("/search")
    public String brandModelsResult(@RequestParam("brand") Integer brandID, Model model) {
        if (SecurityService.isUserLoggedIn()) {
            model.addAttribute("loggedInUsername", SecurityService.getLoggedInUsername());
        }

        String brandName = brandService.getBrandById(brandID);
        model.addAttribute("brandName", brandName);

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
    }

    @GetMapping("/searchModel")
    public String modelsResult(@RequestParam("brand") Integer brandID, @RequestParam("model") String modelName, Model model) {
        if (SecurityService.isUserLoggedIn()) {
            model.addAttribute("loggedInUsername", SecurityService.getLoggedInUsername());
        }

        Integer searchedModelId = modelService.getIdByName(modelName);

        model.addAttribute("brandName", modelName);

        List<Listing> listingsFromBrand = new ArrayList<>();

        Iterable<com.neon.model.Model> modelsFromBrand = modelService.getAllFromBrand(brandID);
        for (com.neon.model.Model m : modelsFromBrand){
            Iterable<Listing> modelListings = listingService.findAllFromModel(m.getId());
            for (Listing l : modelListings){
                if (l.getModelId() == searchedModelId)
                    listingsFromBrand.add(l);
            }
        }
        model.addAttribute("listings", listingsFromBrand);
        return "listings/index";
    }

    @GetMapping("/{id}/delete")
    public String deleteListing(Model model, @PathVariable("id") Integer listingId) {
        Listing listing = listingService.findOne(listingId).get();
        Integer sellerId = listing.getSellerId();
        String loggedInUsername = SecurityService.getLoggedInUsername();
        User user = userService.findOneByUsername(loggedInUsername);
        if (sellerId == user.getId()) {
            listingService.delete(listing);
        }
        return "redirect:/users/my-account";
    }

    @GetMapping("/{id}/buy")
    public String buy(@PathVariable("id") Integer listingId, Model model){
        //Remover Do Vendedor.
        //        //Adicionar ao comprador
        //        //
        return "index";
    }
}
