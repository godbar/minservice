package com.aniu.redis.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aniu.redis.dao.UserDao;
import com.aniu.redis.entity.User;
import com.aniu.redis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserDao userDao;

    @Resource(name = "redisStringTemplate")
    RedisTemplate redisTemplate;

    @Override
    public User selectUserById(Integer id) {

        long start = System.currentTimeMillis();

        String key = "key:"+id;
        //判断缓存中有没有
        boolean hasKey = redisTemplate.hasKey(key.trim());
        //没有查数据库，设置缓存
        User user= null;
        ValueOperations<String,String> valueOperations = null;
        if(hasKey){
            valueOperations = redisTemplate.opsForValue();
            String userstr = valueOperations.get(key);
            user = JSONObject.parseObject(userstr,User.class);
        } else {
            user = userDao.selectUserById(id);
            String userstr = JSONObject.toJSONString(user);
            valueOperations = redisTemplate.opsForValue();
            System.out.println("userstr "+ userstr);
            valueOperations.set(key,userstr.trim());
        }
        System.out.println("key "+ key);
        System.out.println(System.currentTimeMillis()-start);
        return user;
    }

    @Override
    public void insertUser(User user) {
        userDao.insertUser(user);
    }
}
