package com.exercise.datasourceswitch02.mapper;

import com.exercise.datasourceswitch02.CurDataSource;
import com.exercise.datasourceswitch02.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @CurDataSource(name= "second")
    @Select("select * from tb_user where username = #{id}")
    List<User> getByName(String name);

    @Insert("insert into tb_user(username, protrait, sex, usertype) values(#{userName},#{protrait},#{sex}, #{userType})")
    void insertUser(User user);
}
