package com.aniu.redis.dao;

import com.aniu.redis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {

    User selectUserById(@Param("id") Integer id);

    void insertUser(@Param("user") User user);
}
