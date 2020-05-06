package com.cl.springboot.service;

import com.cl.springboot.dto.CommentDTO;
import com.cl.springboot.mapper.CommentMapper;
import com.cl.springboot.mapper.QuestionMapper;
import com.cl.springboot.mapper.UserMapper;
import com.cl.springboot.model.Comment;
import com.cl.springboot.model.Question;
import com.cl.springboot.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommentMapper commentMapper;

    public List<CommentDTO> getListById(Integer id) {
        Question question = questionMapper.getById(id);
        List<Comment> commentList = commentMapper.getList();
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment comment:commentList) {
            if (question.getId().equals(comment.getQuestionId())) {
                User user = userMapper.findById(comment.getCreator());
                CommentDTO commentDTO = new CommentDTO();
                BeanUtils.copyProperties(comment,commentDTO);
                commentDTO.setQuestion(question);
                commentDTO.setUser(user);
                commentDTOList.add(commentDTO);
            }
        }
        return commentDTOList;
    }
}
