package com.cl.springboot.model;

import lombok.Data;

@Data
public class User {

  private Integer id;
  private String name;
  private String accountId;
  private String token;
  private long gmtCreate;
  private long gmtModified;
  private String bio;
  private String avatar_url;


}