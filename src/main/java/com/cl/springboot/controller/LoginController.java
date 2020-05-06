package com.cl.springboot.controller;

import com.cl.springboot.mapper.User2Mapper;
import com.cl.springboot.model.User2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private User2Mapper user2Mapper;

    @GetMapping("/login")
    public String toLogin() {
        return "login";
    }

    @PostMapping("/user/login")
    public String login(@RequestParam(name = "username") String username,
                        @RequestParam(name = "password") String password ,
                        Model model) {
        List<User2> list = user2Mapper.list();
        for (User2 user2:list) {
            if (user2.getUsername().equals(username)&&user2.getPassword().equals(password)) {
                User2 users = new User2();
                users.setPassword(password);
                users.setUsername(username);
                model.addAttribute("users",users);
                return "redirect:/";
            }
        }
        return "login";
    }
}
