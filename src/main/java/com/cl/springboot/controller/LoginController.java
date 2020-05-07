package com.cl.springboot.controller;

import com.cl.springboot.mapper.User2Mapper;
import com.cl.springboot.model.User2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
                        Model model,
                        HttpServletRequest request) {
        List<User2> list = user2Mapper.list();
        for (User2 user:list) {
            if (user.getUsername().equals(username)&&user.getPassword().equals(password)) {
                User2 user2 = new User2();
                user2.setPassword(password);
                user2.setUsername(username);
                model.addAttribute("user2",user2);
                request.getSession().setAttribute("user2",user2);
                return "redirect:/";
            }
        }
        return "login";
    }
}
