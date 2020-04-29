package com.cl.springboot.mapper;

import com.cl.springboot.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface QuestionMapper {

    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,common_count,view_count,like_count,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{commonCount},#{viewCount},#{likeCount},#{tag})")
    void create(Question question);
}
