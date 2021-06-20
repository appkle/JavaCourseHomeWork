package com.exercise.datasourceswitch01.service;

import com.exercise.datasourceswitch01.card.NewUserRequest;
import com.exercise.datasourceswitch01.mapper.master.UserMapper;
import com.exercise.datasourceswitch01.mapper.slave.SlaveUserMapper;
import com.exercise.datasourceswitch01.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserMapper masterUserMapper;

    @Autowired
    SlaveUserMapper slaveUserMapper;

    public void insertUser(NewUserRequest newUserRequest){
        User user = User.builder()
                .userName(newUserRequest.getUserName())
                .protrait("www.kk.com")
                .sex(newUserRequest.getSex().equals("男") ? 0 : 1)
                .userType(0)
                .build();
        masterUserMapper.insertUser(user);
        System.out.println("输出信息:"+user);
    }

    public List<User> getUserByName(String name){
        List<User> users =  slaveUserMapper.getByName(name);
        return users;
    }
}
