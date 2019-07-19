package com.lai.jwtdemo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lai.jwtdemo.pojo.User;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: bbdw
 * @Date: 2019/7/17 9:57
 * @Version 1.0
 */
@Component
public class Jwtutil {
    /**
     * JWT包含了三部分：
     * Header 头部(标题包含了令牌的元数据，并且包含签名和/或加密算法的类型)
     * Payload 负载 (类似于飞机上承载的物品)
     * Signature 签名/签证
     */
    /**
     * 生成token的方法
     * @param user
     * @return
     */
    public String getToken(User user){
        //Algorithm.HMAC256():使用HS256生成token,密钥则是用户的密码，唯一密钥的话可以保存在服务端。
        //withAudience()存入需要保存在token的信息，这里我把用户ID存入token中
        String token ="";
        token = JWT.create().withAudience(String.valueOf(user.getId()))
                .withExpiresAt(new Date(System.currentTimeMillis()+10*60*1000)) //10分钟有效
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }

}
