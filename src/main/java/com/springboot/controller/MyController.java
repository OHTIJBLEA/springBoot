package com.springboot.controller;

import com.springboot.model.User;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MyController {

    private final UserService userService;

    @Autowired
    public MyController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String showAllEmployees(Model model) {
        List<User> empl = userService.findAll();
        model.addAttribute("allUser", empl);
        return "all-users";
    }

    @GetMapping("/user-save")
    public String saveUserForm(User user) {
        return "user-save";
    }

    @PostMapping("/user-save")
    public String saveUser(User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @DeleteMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateEmployee(User user) {
        userService.saveUser(user);
        return "redirect:/";
    }
}
