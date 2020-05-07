package com.cl.springboot.service;

import com.cl.springboot.dto.QuestionDTO;
import com.cl.springboot.mapper.QuestionMapper;
import com.cl.springboot.mapper.UserMapper;
import com.cl.springboot.model.Question;
import com.cl.springboot.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;


    public List<QuestionDTO> list() {
        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question: questions) {
           User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }


    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.getById(id);
        User user = userMapper.findById(question.getCreator());
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void addRead(Integer id) {
        Question question = questionMapper.getById(id);
        question.setViewCount(question.getViewCount()+1);
        questionMapper.addRead(question);
    }

    public void addLikeCount(Integer id) {
        Question question = questionMapper.getById(id);
        question.setLikeCount(question.getLikeCount()+1);
        questionMapper.addLike(question);
    }

    public void addCommentCount(Integer id) {
        Question question = questionMapper.getById(id);
        question.setCommentCount(question.getCommentCount()+1);
        questionMapper.addComment(question);
    }

    public void deleteById(Integer id) {
        questionMapper.deleteById(id);
    }
}
