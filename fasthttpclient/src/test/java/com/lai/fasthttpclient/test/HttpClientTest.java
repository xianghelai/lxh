package com.lai.fasthttpclient.test;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * @Author: bbdw
 * @Date: 2019/7/15 11:35
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpClientTest {

    /**
     * 不带参数的get
     * @throws IOException
     */
    @Test
    public void getMethod() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8081/alipaytest/test");
        CloseableHttpResponse response = client.execute(httpGet);
        if(200 == response.getStatusLine().getStatusCode()){
            //解析响应，获取数据
            String res = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(res);
        }
        response.close();
        client.close();
    }
    /**
     * 带参数的get请求
     * @throws IOException
     */
    @Test
    public void getMethod2() throws IOException, URISyntaxException {
        CloseableHttpClient client = HttpClients.createDefault();
        URI url = new URIBuilder("http://localhost:8081/alipaytest/test")
                .setParameter("username", "lailai")
                .setParameter("pass", "172838").build();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = client.execute(httpGet);
        if(200 == response.getStatusLine().getStatusCode()){
            //解析响应，获取数据
            String res = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(res);
        }
        response.close();
        client.close();
    }

    /**
     * 不带参数post
     * @throws IOException
     */
    @Test
    public void postMethod() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://www.oschina.net/");
        //伪装成浏览器，否则开源中国会拦截
        httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        CloseableHttpResponse response = client.execute(httpPost);
        if(response.getStatusLine().getStatusCode()==200){
            HttpEntity entity = response.getEntity();
            //解析entity
            String s = EntityUtils.toString(entity);
            System.out.println(s);
        }
        //释放资源
        response.close();
        client.close();
    }
    /**
     * 带参数post,表单方式
     * @throws IOException
     */
    @Test
    public void postMethod2() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8081/alipaytest/test");
        //封装参数
        ArrayList<NameValuePair> pairs = new ArrayList<>();
        pairs.add(new BasicNameValuePair("username","lai"));
        pairs.add(new BasicNameValuePair("pass","java"));
        //g构造form表单实体
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(pairs);
        httpPost.setEntity(formEntity);

        CloseableHttpResponse response = client.execute(httpPost);
        HttpEntity entity = response.getEntity();
        //解析entity
        String s = EntityUtils.toString(entity);
        System.out.println("===================================="+s);
        //释放资源
        response.close();
        client.close();
    }

    @Autowired
    private CloseableHttpClient httpClient;
    @Autowired
    private RequestConfig requestConfig;
    /**
     * http连接池使用
     */
    @Test
    public void dopost3() throws IOException {
        HttpPost httpPost = new HttpPost("http://localhost:8081/alipaytest/test");
        //封装参数
        ArrayList<NameValuePair> pairs = new ArrayList<>();
        pairs.add(new BasicNameValuePair("username","lai"));
        pairs.add(new BasicNameValuePair("pass","java"));
        //g构造form表单实体
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(pairs);
        httpPost.setEntity(formEntity);
        httpPost.setConfig(requestConfig);

        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        //解析entity
        String s = EntityUtils.toString(entity);
        System.out.println("===================================="+s);

    }
}
