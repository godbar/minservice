package com.aniu.redis.controller;

import com.aniu.redis.entity.User;
import com.aniu.redis.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("select/{id}")
    public User selectUserById(@PathVariable("id") Integer id){

        return userService.selectUserById(id);
    }

    @GetMapping("insert/{id}")
    public void insertUser(@PathVariable("id") Integer id){

        User user = new User();
        user.setId(id);
        user.setUserName("aniu");
        user.setPassword("password");
        userService.insertUser(user);
    }

}
