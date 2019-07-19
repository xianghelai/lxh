package com.lai.jwtdemo.service;


import com.lai.jwtdemo.pojo.User;

/**
 * @Author: bbdw
 * @Date: 2019/7/17 10:53
 * @Version 1.0
 */
public interface UserService {
    //根据id查找用户
    public User findById(String id);

    public User findByUsername(User user);
}
