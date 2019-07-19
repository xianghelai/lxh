package com.lai.jwtdemo.serviceImpl;

import com.lai.jwtdemo.mapper.UserMapper;
import com.lai.jwtdemo.service.UserService;
import com.lai.jwtdemo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: bbdw
 * @Date: 2019/7/17 10:54
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User findById(String id) {
        return userMapper.selectByPrimaryKey(Integer.parseInt(id));
    }

    @Override
    public User findByUsername(User user) {
        User byUsername = userMapper.findByUsername(user);
        return byUsername;
    }
}
