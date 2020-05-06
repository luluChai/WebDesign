package com.cl.springboot.mapper;

import com.cl.springboot.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {

    @Insert("insert into comment(content,gmt_create,gmt_modified,creator,question_id) values(#{content},#{gmtCreate},#{gmtModified},#{creator},#{questionId})")
    void create(Comment comment);

    @Select("select * from comment")
    List<Comment> getList();

    @Select("select * from comment where question_id = #{id}")
    List<Comment> getListById(Integer id);
}
