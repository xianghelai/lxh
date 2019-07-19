package com.lai.fasthttpclient.test;

import com.alibaba.fastjson.JSONObject;
import com.lai.fasthttpclient.config.HttpClientCfg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
/**
 * @Author: bbdw
 * @Date: 2019/7/15 16:59
 * @Version 1.0
 */
@Component
public class HttpClientTest {
    @Autowired
    private static HttpClientCfg httpClientCfg;

    public static void main(String[] args) {

    }
}
