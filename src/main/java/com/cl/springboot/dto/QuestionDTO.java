package com.cl.springboot.dto;

import com.cl.springboot.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private long gmtCreate;
    private long gmtModified;
    private Integer creator;
    private Integer commonCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private User user;
}
