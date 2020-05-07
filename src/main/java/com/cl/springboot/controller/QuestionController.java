package com.cl.springboot.controller;

import com.cl.springboot.dto.CommentDTO;
import com.cl.springboot.dto.QuestionDTO;
import com.cl.springboot.service.CommentService;
import com.cl.springboot.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/describe/{id}")
    public String describe(@PathVariable(value = "id") Integer id, Model model) {
        QuestionDTO question = questionService.getById(id);
        questionService.addRead(id);
        model.addAttribute("question", question);
        List<CommentDTO> comments = commentService.getListById(id);
        model.addAttribute("comments",comments);
        return "describe";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(value = "id") Integer id,
                       Model model) {
        QuestionDTO questionDTO = questionService.getById(id);
        model.addAttribute("queryQuestion",questionDTO);
        return "public";
    }
    @GetMapping("/like/{id}")
    public String like(@PathVariable(value = "id")Integer id) {
       questionService.addLikeCount(id);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id")Integer id){
        questionService.deleteById(id);
        return "redirect:/";
    }
}
