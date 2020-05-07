package com.cl.springboot.controller;

import com.cl.springboot.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AnswerController {

    @Autowired
    private AnswerService answerService;

   /* @GetMapping("/answer/{id}")
    public String answer(@PathVariable(value = "id")Integer id) {
        answerService.addAnswer(id);

    }*/
}
