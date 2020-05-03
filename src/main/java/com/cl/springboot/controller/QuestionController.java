package com.cl.springboot.controller;

import com.cl.springboot.dto.QuestionDTO;
import com.cl.springboot.mapper.CommentMapper;
import com.cl.springboot.mapper.QuestionMapper;
import com.cl.springboot.model.Comment;
import com.cl.springboot.model.Question;
import com.cl.springboot.model.User;
import com.cl.springboot.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class QuestionController {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentMapper commentMapper;


    @GetMapping("/describe/{id}")
    public String describe(@PathVariable(value = "id")Integer id, Model model) {
        QuestionDTO question = questionService.getById(id);
        model.addAttribute("question",question);
        return "describe";
    }
    @PostMapping("/comment")
    public String comment(@RequestParam(name = "content")String content,
                          HttpServletRequest request,
                          Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "登录后才可以评论哦~");
            return "describe";
        }
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCreator(user.getId());
        commentMapper.create(comment);
        model.addAttribute("comment",comment);
        List<Question> questions = questionMapper.list();
        model.addAttribute("question",questions);
        return "redirect:/";
    }

}
