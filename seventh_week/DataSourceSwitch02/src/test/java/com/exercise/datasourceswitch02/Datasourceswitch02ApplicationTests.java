package com.exercise.datasourceswitch02;

import com.exercise.datasourceswitch02.mapper.UserMapper;
import com.exercise.datasourceswitch02.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Datasourceswitch02ApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    void insertUser() {
        User user = User.builder()
                .userName("houjia")
                .protrait("...")
                .sex(0)
                .userType(0)
                .build();
        userMapper.insertUser(user);
        System.out.println("输出信息:"+user);
    }

    @Test
    public void getTest(){
        List<User> users = userMapper.getByName("zhanghua");
        System.out.println("输出信息:" + users);
    }
}
