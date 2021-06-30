package com.example.shardingspherexa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(TransactionConfiguration.class)
public class ShardingsphereXaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ShardingsphereXaApplication.class, args);
    }

    @Autowired
    private OrderService orderService;

    public void run(String... args) throws Exception {
        int count = orderService.selectAll();
        System.out.println(count);
        //orderService.insertFailed(10);
        orderService.insert(10);
        count = orderService.selectAll();
        System.out.println(count);
    }
}
