package com.lai.redisdemo.test;

import com.lai.redisdemo.RedisUtils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @Author: bbdw
 * @Date: 2019/7/16 16:27
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisUtil redisUtil;
    @Test
    public void testAll(){
        //String类型
        boolean result1 = redisUtil.set("lai:bbdw", "我是string类型");
        System.out.println(redisUtil.get("lai:bbdw"));

        //map hash
        HashMap<String, String> map = new HashMap<>();
        map.put("map1","1");
        map.put("map2","12");
        map.put("map3","123");
        map.put("map4","1234");
        boolean result2 = redisUtil.hmset("hash类型", map);
        System.out.println(redisUtil.hmget("hash类型"));

        //list
        List<String> list = new ArrayList<>();
        list.add("dsla1");
        list.add("dsla2");
        list.add("dsla3");
        list.add("dsla4");
        redisUtil.setList("我是List",list);
        System.out.println(redisUtil.getList("我是List",12l));
        System.out.println(redisUtil.getListAll("我是List"));
        redisUtil.remove("我是List");
        System.out.println(redisUtil.getListAll("我是List"));

        //set
        HashSet<String> set = new HashSet<>();
        set.add("11");
        set.add("22");
        set.add("33");
        set.add("44");
        boolean result3 = redisUtil.setSet("我是set", set);
        System.out.println(redisUtil.getSet("我是set"));

        //zset
        redisUtil.setzSet("zset","111",1d);
        redisUtil.setzSet("zset","222",2d);
        redisUtil.setzSet("zset","333",3d);
        redisUtil.setzSet("zset","444",1d);
        System.out.println(redisUtil.getZSet("zset",0,5));
    }

}
