package com.exercise.datasourceswitch01;

import com.exercise.datasourceswitch01.mapper.master.UserMapper;
import com.exercise.datasourceswitch01.mapper.slave.SlaveUserMapper;
import com.exercise.datasourceswitch01.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Datasourceswitch01ApplicationTests {
    @Autowired
    UserMapper masterUserMapper;
    @Autowired
    SlaveUserMapper slaveUserMapper;

    @Test
    public void insertTest(){
        User user = User.builder()
                .userName("heiheihei")
                .protrait("...")
                .sex(0)
                .userType(0)
                .build();
        masterUserMapper.insertUser(user);
        System.out.println("输出信息:"+user);
    }

    @Test
    public void getUserTest(){
        List<User> users = slaveUserMapper.getByName("liliu");
        System.out.println("输出信息:" + users);
    }
}
