package com.cl.springboot.mapper;

import com.cl.springboot.dto.QuestionDTO;
import com.cl.springboot.model.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper {

    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{commentCount},#{viewCount},#{likeCount},#{tag})")
    void create(Question question);

    @Select("select id,title,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count,tag from question order by id  desc")
    List<Question> list();

    @Select("select * from question where id = #{id}")
    Question getById(@Param("id") Integer id);

    @Update("update question set view_count=#{viewCount} where id = #{id}")
    void addRead(Question question);

    @Update("update question set like_count = #{likeCount} where id = #{id}")
    void addLike(Question question);

    @Update("update question set comment_count = #{commentCount} where id = #{id}")
    void addComment(Question question);

    @Delete("delete from question where id =#{id}")
    void deleteById(Integer id);

    @Update("update question set title=#{title},description=#{description},gmt_create=#{gmtCreate},gmt_modified=#{gmtModified},tag=#{tag} where id =#{id}")
    void updateQuestion(QuestionDTO question);
}
