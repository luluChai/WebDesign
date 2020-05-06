package com.cl.springboot.mapper;

import com.cl.springboot.model.User2;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface User2Mapper {
    @Select("select * from user2")
    List<User2> list();
}
