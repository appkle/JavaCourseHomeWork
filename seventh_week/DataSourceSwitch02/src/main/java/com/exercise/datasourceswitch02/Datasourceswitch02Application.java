package com.exercise.datasourceswitch02;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

@MapperScan("com.exercise.datasourceswitch02.mapper")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Import({DynamicDataSourceConfig.class})
public class Datasourceswitch02Application {
    public static void main(String[] args) {
        SpringApplication.run(Datasourceswitch02Application.class, args);
    }
}
