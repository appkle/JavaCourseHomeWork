package com.exercise.datasourceswitch01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Datasourceswitch01Application {

    public static void main(String[] args) {
        SpringApplication.run(Datasourceswitch01Application.class, args);
    }
}
