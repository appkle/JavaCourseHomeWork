package com.exercise.datasourceswitch01.pojo;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
public class User {
    private int id;
    private String userName;
    private String protrait;
    private int sex;
    private int userType;
}
