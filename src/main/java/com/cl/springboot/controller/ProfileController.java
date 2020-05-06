package com.cl.springboot.controller;

import com.cl.springboot.dto.QuestionDTO;
import com.cl.springboot.model.User;
import com.cl.springboot.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;


    @GetMapping("/profile/{action}")
    public String profile (@PathVariable(name = "action")String action, Model model,HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录~");
            return "redirect:/";
        }

        if ("questions".equals(action)) {
           model.addAttribute("section","question");
           model.addAttribute("sectionName","我的问题");
        }else if ("replies".equals(action)) {
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }
        List<QuestionDTO> allQuestions= questionService.list();
        ArrayList<QuestionDTO> questionList = new ArrayList<>();
        Integer userId = ((User) request.getSession().getAttribute("user")).getId();
        for (QuestionDTO question:allQuestions) {
            if (userId.equals(question.getCreator())) {
                questionList.add(question);
                model.addAttribute("question", questionList);
            }
        }
        return "profile";
    }
}
