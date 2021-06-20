package com.exercise.datasourceswitch01.controller;

import com.exercise.datasourceswitch01.card.NewUserRequest;
import com.exercise.datasourceswitch01.pojo.User;
import com.exercise.datasourceswitch01.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public void insertUser(@RequestBody NewUserRequest newUserRequest){
        userService.insertUser(newUserRequest);
    }

    @GetMapping("/")
    public List<User> getUserByName(@RequestParam("name") String name){
        return userService.getUserByName(name);
    }

}
