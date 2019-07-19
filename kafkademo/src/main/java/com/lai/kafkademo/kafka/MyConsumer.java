package com.lai.kafkademo.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: bbdw
 * @Date: 2019/6/5 16:38
 * @Version 1.0
 */
@Component
public class MyConsumer {

    @KafkaListener(topics = "test01")
    public void getMyTopic(ConsumerRecord consumerRecord) throws InterruptedException {
        System.out.println(consumerRecord.toString());
        System.out.println(consumerRecord.value());
        Thread.sleep(3000);
    }
}
