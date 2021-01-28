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
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public String index1(@ModelAttribute User user, Model model) {
        User admin = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("admin", admin);
        model.addAttribute("users", userService.getAllUsers());
        List<Role> allRoles = userService.getRoles();
        model.addAttribute("allRoles", allRoles);
        return "admin";
    }

}
