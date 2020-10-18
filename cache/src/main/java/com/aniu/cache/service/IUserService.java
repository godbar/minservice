package com.aniu.cache.service;

import com.aniu.cache.entity.User;

public interface IUserService {

    User selectUserById(Integer id);

    void insertUser(User user);
}
