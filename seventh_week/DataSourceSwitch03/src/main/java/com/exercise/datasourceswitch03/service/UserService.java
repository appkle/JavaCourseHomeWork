package com.exercise.datasourceswitch03.service;

import com.exercise.datasourceswitch03.card.NewUserRequest;
import com.exercise.datasourceswitch03.pojo.User;
import com.exercise.datasourceswitch03.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void insertUser(NewUserRequest newUserRequest){
        User user = User.builder()
                .userName(newUserRequest.getUserName())
                .protrait("www.kk.com")
                .sex(newUserRequest.getSex().equals("男") ? 0 : 1)
                .userType(0)
                .build();
        userRepository.insertUser(user);
        System.out.println("输出信息:"+user);
    }

    public List<User> getUserByName(String name){
        List<User> users =  userRepository.getUserByName(name);
        return users;
    }
}
