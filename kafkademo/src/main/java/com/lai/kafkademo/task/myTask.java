package com.lai.kafkademo.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.UUID;

/**
 * @Author: bbdw
 * @Date: 2019/6/6 15:10
 * @Version 1.0
 */
//定时生产者生产消息
@EnableScheduling
@Component
public class myTask {
    @Autowired
    private KafkaTemplate kafkaTemplate;
    //@Scheduled(cron = "0/3 * * * * ?")
    public void sendM(){
        String s = UUID.randomUUID().toString();
        ListenableFuture test01 = kafkaTemplate.send("test01", s);
        test01.addCallback(o -> System.out.println("send-消息发送成功：" + s), throwable -> System.out.println("消息发送失败：" + s));
    }
}
