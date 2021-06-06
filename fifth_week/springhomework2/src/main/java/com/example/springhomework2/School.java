package com.example.springhomework2;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;
import java.security.cert.TrustAnchor;

@Configuration
@Data
@EnableConfigurationProperties(Klass.class)
public class School implements ISchool {
    @Autowired
    Klass class1;

    @Override
    public void ding(){
        System.out.println("Class1 have " + this.class1.getStudents().size() + " students");
    }

}
