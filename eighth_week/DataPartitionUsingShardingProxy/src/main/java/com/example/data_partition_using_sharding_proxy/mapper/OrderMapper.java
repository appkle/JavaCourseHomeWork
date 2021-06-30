package com.example.data_partition_using_sharding_proxy.mapper;

import com.example.data_partition_using_sharding_proxy.model.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    void insert(Order order);
    Order getOrderById(long orderId);
    List<Order> getOrderByUserId(int userId);
    void updateOrderById(Order order);
    void deleteOrder(long orderId);
}
