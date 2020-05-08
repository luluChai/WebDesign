package com.cl.springboot.controller;

import com.cl.springboot.dto.QuestionDTO;
import com.cl.springboot.mapper.User2Mapper;
import com.cl.springboot.mapper.UserMapper;
import com.cl.springboot.model.User;
import com.cl.springboot.model.User2;
import com.cl.springboot.service.LoginService;
import com.cl.springboot.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private User2Mapper user2Mapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/login")
    public String toLogin() {
        return "login";
    }

    @PostMapping("/user/login")
    public String login(@RequestParam(name = "username") String username,
                        @RequestParam(name = "password") String password,
                        Model model,
                        HttpServletRequest request) {
        List<User2> list = user2Mapper.list();
        for (User2 user : list) {
            if (username.equals("admin") && password.equals("123")) {
                User2 user2 = new User2();
                user2.setName("admin");
                user2.setPassword("123");
                request.getSession().setAttribute("user",user2);
                List<QuestionDTO> questionDTOS = questionService.list();
                model.addAttribute("questions",questionDTOS);
                List<User2> user2List = user2Mapper.list();
                model.addAttribute("users",user2List);
                return "manage";
            }
            if (user.getName().equals(username) && user.getPassword().equals(password)) {
                User2 user2 = new User2();
                user2.setPassword(password);
                user2.setName(username);
                request.getSession().setAttribute("user", user2);
                model.addAttribute("user", user2);

                return "redirect:/";
            }
        }
        return "login";
    }

    @GetMapping("/signOut")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {

        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("user2");
        return "login";
    }

}
