package com.cl.springboot.mapper;



import com.cl.springboot.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified,bio) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{bio})")
     void insert(User user);

    @Select("select * from user where token= #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id= #{id}")
    User findById(@Param("id") Integer id);

    @Select("select * from user")
    User list();

    @Select("select name,password from user")
    List<User> oAuth(String username, String password);
}
