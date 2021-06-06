package com.example.springhomework2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;

@SpringBootTest(classes = Springhomework2ApplicationTests.class)
@SpringBootApplication
@ActiveProfiles("common")
class Springhomework2ApplicationTests {
    @Resource
    private School school;
    @Test
    public void assertcontextLoads(){
        school.ding();
    }

}
