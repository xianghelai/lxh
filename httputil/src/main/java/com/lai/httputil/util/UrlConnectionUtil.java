package com.lai.httputil.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author: bbdw
 * @Date: 2019/7/11 10:27
 * @Version 1.0
 */
public class UrlConnectionUtil {
    //请求参数直接拼接url后面
    public static String executeGet(String url) throws Exception {
        //定义url
        URL u = new URL(url);
        //根据url获取连接对象
        HttpURLConnection conn = (HttpURLConnection)u.openConnection();
        //设置请求参数:
        //是否向httpurlconn输出,传入参使用，是否向httpurlconn输出，设置为true时强制改为POST请求
        conn.setDoOutput(false);
        //是否向httpurlconn读入
        conn.setDoInput(true);
        //设置请求方式（get，post，put，delete登）大写
        conn.setRequestMethod("GET");
        //是否使用缓存
        conn.setUseCaches(true);
        //设置超时时间
        conn.setConnectTimeout(12000);
        //执行连接
        conn.connect();
        //获取响应码
        int code = conn.getResponseCode();
        //从流中读取响应信息
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuffer msg = new StringBuffer();
        String line = "";
        while((line = reader.readLine())!=null){
            msg.append(line);
        }
        //释放资源
        reader.close();
        conn.disconnect();
        System.out.println("[http请求返回]"+msg);
        return msg.toString();
    }
    public static String executePost(String url,String params) throws Exception {
        //定义url
        URL u = new URL(url);
        //根据url获取连接对象
        HttpURLConnection conn = (HttpURLConnection)u.openConnection();
        //设置请求参数:
        //是否向httpurlconn输出,传入参使用，是否向httpurlconn输出，设置为true时强制改为POST请求
        conn.setDoOutput(true);
        //是否向httpurlconn读入
        conn.setDoInput(true);
        //设置请求方式（get，post，put，delete登）大写
        conn.setRequestMethod("POST");
        //是否使用缓存
        conn.setUseCaches(true);
        //设置超时时间
        conn.setConnectTimeout(12000);
        //执行连接
        conn.connect();
        OutputStream stream = conn.getOutputStream();
        //传入请求参数
        stream.write(params.getBytes());
        //获取响应码
        int code = conn.getResponseCode();
        //从流中读取响应信息
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuffer msg = new StringBuffer();
        String line = "";
        while((line = reader.readLine())!=null){
            msg.append(line);
        }
        //释放资源
        reader.close();
        conn.disconnect();
        System.out.println("[http请求返回]"+msg);
        return msg.toString();
    }
}
