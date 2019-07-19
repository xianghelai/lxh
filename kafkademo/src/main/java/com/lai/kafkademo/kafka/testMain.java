package com.lai.kafkademo.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: bbdw
 * @Date: 2019/6/5 16:43
 * @Version 1.0
 */
@RestController
public class testMain {
    @Autowired
    MyProducer producer ;

    @RequestMapping("test01")
    public  void test01(String value) {
        producer.sendKafka(value);
    }
}
