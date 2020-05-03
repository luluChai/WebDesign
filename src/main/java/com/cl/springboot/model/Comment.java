package com.cl.springboot.model;

import lombok.Data;

@Data
public class Comment {

  private Integer id;
  private String content;
  private long gmtCreate;
  private long gmtModified;
  private Integer creator;

}
