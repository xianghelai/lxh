package com.lai.httputil.test;

import com.alibaba.fastjson.JSONObject;
import com.lai.httputil.util.HttpclientUtil;
import org.apache.http.entity.StringEntity;

import java.io.IOException;

/**
 * @Author: bbdw
 * @Date: 2019/7/11 14:30
 * @Version 1.0
 */
public class HttpClientTest {
    public static void main(String[] args) throws Exception {
        HttpclientUtil.doGet("http://localhost:8081/alipaytest/test?username=dsjad");
        HttpclientUtil.doPost("http://localhost:8081/alipaytest/test","username=lailai&pass=1232424");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("KEY1", "VALUE1");
        jsonObject.put("KEY2", "VALUE2");
        System.out.println(jsonObject.toString());
        new StringEntity(jsonObject.toString());
    }
}
