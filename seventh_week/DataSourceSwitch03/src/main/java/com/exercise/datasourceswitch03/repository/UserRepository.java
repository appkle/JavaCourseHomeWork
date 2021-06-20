package com.exercise.datasourceswitch03.repository;

import com.exercise.datasourceswitch03.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRepository {
    List<User> getUserByName(String name);
    void insertUser(User user);
}
