package com.exercise.datasourceswitch02.pojo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {
    private int id;
    private String userName;
    private String protrait;
    private int sex;
    private int userType;
}
