package com.cl.springboot.model;

import lombok.Data;

@Data

public class Answer {

  private Integer id;
  private String content;
  private Integer gmtCreate;
  private Integer gmtModified;
  private Integer commentId;
  private Integer questionId;
}
