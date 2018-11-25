package com.neon.controller;

import com.neon.model.Listing;
import com.neon.model.User;
import com.neon.security.SecurityUtils;
import com.neon.service.ListingService;
import com.neon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ListingService listingService;

    @GetMapping(value = "/new")
    public String create(@ModelAttribute User user){
        if (SecurityUtils.isUserLoggedIn()){
            return "redirect:/users/my-account";
        }
        return "/user/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute User entityUser, BindingResult result, RedirectAttributes redirectAttributes) {
        User user =  userService.save(entityUser);
        return "redirect:/users/" + user.getId();
    }

    // Tela de Show User
    @GetMapping("/my-account")
    public String show(Model model) {
        String loggedInUsername = SecurityUtils.getLoggedInUsername();
        User user = userService.findOneByUsername(loggedInUsername);
        Iterable<Listing> myListings = listingService.findAllBySellerId(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("listListing", myListings);
        return "/user/show";
    }

    @GetMapping("/edit-account")
    public String update(Model model) {
        String loggedInUsername = SecurityUtils.getLoggedInUsername();
        User user = userService.findOneByUsername(loggedInUsername);
        model.addAttribute("user", user);
        return "user/form";
    }

    @PutMapping
    public String update(@Valid @ModelAttribute User entityUser) {
        userService.save(entityUser);
        return "redirect:/users/my-account";
    }

    @RequestMapping("/delete")
    public String delete() {
        String loggedInUsername = SecurityUtils.getLoggedInUsername();
        User user = userService.findOneByUsername(loggedInUsername);
        userService.delete(user);
        return "redirect:/";
    }
}
