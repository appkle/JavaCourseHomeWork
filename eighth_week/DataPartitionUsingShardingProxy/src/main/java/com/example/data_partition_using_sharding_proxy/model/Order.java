package com.example.data_partition_using_sharding_proxy.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
@Builder
public class Order {
    private long id;
    private int userId;
    private Date createTime;
    private int state;
    private String receiverName;
    private String region;
    private String address;
    private String phone;
}
