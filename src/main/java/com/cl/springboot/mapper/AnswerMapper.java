package com.cl.springboot.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AnswerMapper {

    @Insert("insert into answer()values()")
    void addAnswer(Integer id);
}
