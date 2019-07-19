package com.lai.httputil.test;

import com.lai.httputil.util.HttpclientUtil;
import com.lai.httputil.util.UrlConnectionUtil;
import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Author: bbdw
 * @Date: 2019/7/11 10:28
 * @Version 1.0
 */
public class UrlConnectiontest {
    public static void main(String[] args) throws Exception {
        UrlConnectionUtil.executeGet("http://localhost:8081/alipaytest/test?username=dsjad");
        UrlConnectionUtil.executePost("http://localhost:8081/alipaytest/test","username=dsjad&pass=123");

    }
    //httpclinet
    //get
    public void httpclientGet() throws Exception {

    }
}
