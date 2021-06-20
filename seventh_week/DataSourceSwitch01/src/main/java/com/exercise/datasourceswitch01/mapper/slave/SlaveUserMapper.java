package com.exercise.datasourceswitch01.mapper.slave;

import com.exercise.datasourceswitch01.pojo.User;

import java.util.List;

public interface SlaveUserMapper {
    List<User> getByName(String name);
}
