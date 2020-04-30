package com.cl.springboot.mapper;



import com.cl.springboot.model.Question;
import com.cl.springboot.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified,bio,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{bio},#{avatar_url})")
     void insert(User user);

    @Select("select * from user where token= #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where token= #{id}")
    User findById(@Param("id") Integer id);


}
