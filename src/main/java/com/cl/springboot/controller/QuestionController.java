package com.cl.springboot.controller;

import com.cl.springboot.dto.CommentDTO;
import com.cl.springboot.dto.QuestionDTO;
import com.cl.springboot.model.User;
import com.cl.springboot.service.CommentService;
import com.cl.springboot.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
        return "edit";
    }

    @PutMapping("/edit/ok/{id}")
    public String editOk(@PathVariable(value = "id")Integer id,
                         @RequestParam(value = "tag")String tag,
                         @RequestParam(value = "title") String title,
                         @RequestParam(value = "description")String description,
                         Model model,
                         HttpServletRequest request){
        model.addAttribute("tag",tag);
        model.addAttribute("title",title);
        model.addAttribute("description",description);

        if (title == null || title == "") {
            model.addAttribute("error", "标题不能为空！~");
            return "public";
        }
        if (description == null || description == "") {
            model.addAttribute("error", "问题内容不能为空！~");
            return "public";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error", "标签不能为空！~");
            return "public";
        }
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录~");
            return "edit";
        }
        QuestionDTO questionDTO = questionService.getById(id);
        questionDTO.setTitle(title);
        questionDTO.setDescription(description);
        questionDTO.setTag(tag);
        questionService.editOk(questionDTO);
        return "redirect:/";

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
