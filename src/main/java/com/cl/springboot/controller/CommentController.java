package com.cl.springboot.controller;

import com.cl.springboot.dto.CommentDTO;
import com.cl.springboot.dto.QuestionDTO;
import com.cl.springboot.mapper.CommentMapper;
import com.cl.springboot.model.Comment;
import com.cl.springboot.model.User;
import com.cl.springboot.service.QuestionService;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionService questionService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object comment(@RequestBody CommentDTO commentDTO, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "index";
        }
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCreator(user.getId());
        comment.setQuestionId(commentDTO.getQuestionId());
        commentMapper.create(comment);
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("message","成功");
        return objectObjectHashMap;
    }
}