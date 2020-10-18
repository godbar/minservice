package com.aniu.redis.service;

import com.aniu.redis.entity.User;

public interface IUserService {

    User selectUserById(Integer id);

    void insertUser(User user);
}
