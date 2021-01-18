package com.jm.SpringBoot.SpringBoot.controller;


import com.jm.SpringBoot.SpringBoot.model.Role;
import com.jm.SpringBoot.SpringBoot.model.User;
import com.jm.SpringBoot.SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/admin")
    public String index1(@ModelAttribute User user, Model model) {
        User admin = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("admin", admin);
        model.addAttribute("users", userService.getAllUsers());
        List<Role> allRoles = userService.getRoles();
        model.addAttribute("allRoles", allRoles);
        return "admin";
    }


    @PostMapping("admin/new")
    public String newUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }


    @PostMapping("/admin/edit/{id}")
    public String edit(Model model, @PathVariable("id") long id, User user) {
        model.addAttribute("user", userService.show(id));
        model.addAttribute("user", user);
        List<Role> allRoles = userService.getRoles();
        model.addAttribute("allRoles", allRoles);
        userService.save(user);
        return "redirect:/admin";

    }



    @GetMapping("admin/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }


}
