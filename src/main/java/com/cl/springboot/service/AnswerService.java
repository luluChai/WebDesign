package com.cl.springboot.service;

import com.cl.springboot.mapper.AnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {
    @Autowired
    private AnswerMapper answerMapper;

    public void addAnswer(Integer id) {
        answerMapper.addAnswer(id);
    }
}
