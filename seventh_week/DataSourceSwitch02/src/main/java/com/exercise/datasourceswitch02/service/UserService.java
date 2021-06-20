package com.exercise.datasourceswitch02.service;

import com.exercise.datasourceswitch02.CurDataSource;
import com.exercise.datasourceswitch02.card.NewUserRequest;
import com.exercise.datasourceswitch02.mapper.UserMapper;
import com.exercise.datasourceswitch02.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void insertUser(NewUserRequest newUserRequest){
        User user = User.builder()
                .userName(newUserRequest.getUserName())
                .protrait("www.kk.com")
                .sex(newUserRequest.getSex().equals("男") ? 0 : 1)
                .userType(0)
                .build();
        userMapper.insertUser(user);
        System.out.println("输出信息:"+user);
    }

    public List<User> getUserByName(String name){
        List<User> users =  userMapper.getByName(name);
        return users;
    }
}
