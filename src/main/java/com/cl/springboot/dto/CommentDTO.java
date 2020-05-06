package com.cl.springboot.dto;

import com.cl.springboot.model.Question;
import com.cl.springboot.model.User;
import lombok.Data;

@Data
public class CommentDTO {
    private Integer id;
    private String content;
    private long gmtCreate;
    private long gmtModified;
    private Integer creator;
    private Integer questionId;

    private Question question;
    private User user;
}
