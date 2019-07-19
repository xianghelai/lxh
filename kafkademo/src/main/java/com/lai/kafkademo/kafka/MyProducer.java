package com.lai.kafkademo.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: bbdw
 * @Date: 2019/6/5 16:34
 * @Version 1.0
 */
@Component
public class MyProducer {
    @Autowired
    private  KafkaTemplate kafkaTemplate;

    public void sendKafka(String value) {
        kafkaTemplate.send("test01","key01",value);
    }
}
