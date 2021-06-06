package com.example.springhomework2;

import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
public class Student {
    private int id;
    private String name;

}
