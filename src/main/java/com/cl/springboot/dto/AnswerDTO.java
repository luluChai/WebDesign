package com.cl.springboot.dto;

import lombok.Data;

@Data
public class AnswerDTO {
    private Integer id;
    private String content;
    private Integer gmtCreate;
    private Integer gmtModified;
    private Integer commentId;
    private Integer questionId;
    private QuestionDTO questionDTO;
    private CommentDTO commentDTO;
}
