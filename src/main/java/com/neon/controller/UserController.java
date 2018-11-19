package com.neon.controller;

import com.neon.model.Listing;
import com.neon.model.User;
import com.neon.service.ListingService;
import com.neon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ListingService listingService;

    @GetMapping(value = "/new")
    public String create(@ModelAttribute User user){
        return "/user/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute User entityUser, BindingResult result, RedirectAttributes redirectAttributes) {
        User user = null;
        String pagina_retorno = "redirect:/users/" ;
        user = userService.save(entityUser);
        pagina_retorno = pagina_retorno + user.getId();

        return pagina_retorno;
    }

    // Tela de Show User
    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Integer id) {
        if (id != null) {
            User user = userService.findOne(id).get();
            if (user != null) {
                List<Listing> myListings = listingService.findAllFromUser(user);
                model.addAttribute("user", user);
                model.addAttribute("listListing", myListings);
                return "/user/show";
            }
        }
        return "/";
    }

    @GetMapping("/{id}/edit")
    public String update(Model model, @PathVariable("id") Integer userId) {
        Optional<User> user = userService.findOne(userId);
        model.addAttribute("user", user.get());
        return "user/form";
    }

    @PutMapping
    public String update(@Valid @ModelAttribute User user) {
        user = userService.save(user);
        return "redirect:/users/" + user.getId();
    }

    @RequestMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer userId) {
        Optional<User> user = userService.findOne(userId);
        userService.delete(user.get());
        return "redirect:/";
    }
}
