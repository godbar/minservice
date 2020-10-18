package com.aniu.cache.service.impl;

import com.aniu.cache.dao.UserDao;
import com.aniu.cache.entity.User;
import com.aniu.cache.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserDao userDao;

    @Override
    public User selectUserById(Integer id) {

        return userDao.selectUserById(id);
    }

    @Override
    public void insertUser(User user) {
        userDao.insertUser(user);
    }
}
