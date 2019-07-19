package com.lai.jwtdemo.mapper;

import com.lai.jwtdemo.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author: bbdw
 * @Date: 2019/7/18 14:25
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest {
    @Resource
    UserMapper userMapper;
    @Test
    public void testUsermapper(){
        User lai = new User("lai", "123");
        int i = userMapper.insert(lai);
        System.out.println(lai.toString());
    }
}
