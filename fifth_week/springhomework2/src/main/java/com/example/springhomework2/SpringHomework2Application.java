package com.example.springhomework2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringHomework2Application {
    public static void main(String[] args){
        SpringApplication.run(SpringHomework2Application.class, args);
    }

    @Autowired
    Klass klass;
    @Autowired
    School school;
    @Bean
    public void printInfo(){
        klass.dong();
        school.ding();
    }
}
