package com.example.springhomework2;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Import;

import java.util.List;

@Data
@ConfigurationProperties("web")
public class Klass {
    List<Student> students;

    public void dong(){
        System.out.println(getStudents());
    }
}
