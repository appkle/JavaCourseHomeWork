package com.exercise.datasourceswitch01.card;

import lombok.Builder;
import lombok.Data;

@Data
public class NewUserRequest {
    private String userName;
    private String sex;
}
