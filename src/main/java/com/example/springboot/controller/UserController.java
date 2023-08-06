package com.example.springboot.controller;

import com.example.springboot.model.User;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private  final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getAll(Model model){
        List<User> userList = userService.getAll();
        model.addAttribute("myusers",userList);
        return "users";
    }

    @GetMapping("/new")
    public String createUserForm(User ser){
        return "new";
    }

    @PostMapping()
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/";
    }


    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") Long id) {
        model.addAttribute("user", userService.getById(id));
        return "edit";
    }

    @PostMapping ("/edit")
    public String update(User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/";
    }
}
