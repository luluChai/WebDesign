package com.cl.springboot.controller;

import com.cl.springboot.dto.QuestionDTO;
import com.cl.springboot.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    public QuestionService questionService;

    @PostMapping("/search")
    public String search(@RequestParam(value = "search") String search, Model model) {
        List<QuestionDTO> list = questionService.list();
        List<QuestionDTO> searchList = new ArrayList<>();
        for (QuestionDTO questionDTO:list) {
            if ((questionDTO.getTag().equals(search))) {
                QuestionDTO dto = new QuestionDTO();
                BeanUtils.copyProperties(questionDTO,dto);
                dto.setUser(questionDTO.getUser());
                searchList.add(dto);
            }
        }
        model.addAttribute("searchList",searchList);
        return "search";

    }
}
