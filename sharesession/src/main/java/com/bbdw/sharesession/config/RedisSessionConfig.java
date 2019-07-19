package com.bbdw.sharesession.config;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Component;

/**
 * @Author: bbdw
 * @Date: 2019/5/31 9:55
 * @Version 1.0
 */
@Component
@EnableRedisHttpSession
public class RedisSessionConfig {

}
