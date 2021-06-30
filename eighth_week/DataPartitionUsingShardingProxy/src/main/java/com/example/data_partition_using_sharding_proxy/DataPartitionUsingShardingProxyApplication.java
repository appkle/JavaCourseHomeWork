package com.example.data_partition_using_sharding_proxy;

import com.example.data_partition_using_sharding_proxy.mapper.OrderMapper;
import com.example.data_partition_using_sharding_proxy.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class DataPartitionUsingShardingProxyApplication implements CommandLineRunner {
    @Autowired
    OrderMapper orderMapper;

    public static void main(String[] args) {
        SpringApplication.run(DataPartitionUsingShardingProxyApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Order order = Order.builder().userId(4)
                .createTime(new Date()).state(0).receiverName("Tom").region("aa")
                .address("aa.bb").phone("18710368490").build();
        orderMapper.insert(order);
        Order order1 = orderMapper.getOrderById(616250171508371456L);
        System.out.println(order1);
        List<Order> orderList = orderMapper.getOrderByUserId(3);
        System.out.println(orderList);

        orderMapper.deleteOrder(5);
        Order order2 = Order.builder().id(616250171508371456L).userId(4)
                .createTime(new Date()).state(1).receiverName("Tomcat").region("aa")
                .address("aa.cc").phone("13613185508").build();
        orderMapper.updateOrderById(order2);

    }
}
