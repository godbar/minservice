package com.aniu.cache.dao;

import com.aniu.cache.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {

    User selectUserById(@Param("id") Integer id);

    void insertUser(@Param("user") User user);
}
