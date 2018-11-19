package com.neon.controller;

import com.neon.model.User;
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

    // Tela de Show Student
    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Integer id) {
        if (id != null) {
            User user = userService.findOne(id);
            if (user != null) {
                model.addAttribute("user", user);
                return "/user/show";
            }
        }
        return "/";
    }
}
