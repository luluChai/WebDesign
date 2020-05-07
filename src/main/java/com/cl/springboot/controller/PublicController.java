package com.cl.springboot.controller;

import com.cl.springboot.mapper.QuestionMapper;
import com.cl.springboot.model.Question;
import com.cl.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;

@Controller
public class PublicController {

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/public")
    public String publish() {
        return "public";
    }

    @PostMapping("/public")
    public String doPublish(@RequestParam(value = "title", required = false) String title,
                            @RequestParam(value = "description", required = false) String description,
                            @RequestParam(value = "tag", required = false) String tag,
                            HttpServletRequest request,
                            Model model) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

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
            return "public";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        question.setLikeCount(0);
        question.setViewCount(0);
        question.setCommentCount(0);
        questionMapper.create(question);
        return "redirect:/";
    }
}
