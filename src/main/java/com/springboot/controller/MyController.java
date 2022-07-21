package com.springboot.controller;

import com.springboot.model.User;
import com.springboot.service.UserServiceImpl;
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

    private final UserServiceImpl userService;

    @Autowired
    public MyController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String showAllUser(Model model) {
        List<User> user = userService.findAllUsers();
        model.addAttribute("allUser", user);
        return "all-users";
    }

    @GetMapping("/user-save")
    public String saveUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user-save";
    }

    @PostMapping("/user-save")
    public String saveUser(User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @DeleteMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user) {
        userService.saveUser(user);
        return "redirect:/";
    }
}
