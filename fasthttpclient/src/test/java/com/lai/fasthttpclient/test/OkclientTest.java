package com.lai.fasthttpclient.test;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.json.JSONException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: bbdw
 * @Date: 2019/7/12 10:24
 * @Version 1.0
 */
public class OkclientTest {
    OkHttpClient client = new OkHttpClient();

    /**
     * 同步get请求
     * @throws IOException
     */
    @Test
    public void get() throws IOException {
        //构建request
        Request request = new Request.Builder().url("http://localhost:8081/alipaytest/test").build();
        //构建response
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()){
            System.out.println(response.body().toString());
        }else {
            throw new IOException("Unexpected code " + response);
        }

    }

    /**
     *同步post请求
     * @throws IOException
     * @throws JSONException
     */
    @Test
    public void post() throws IOException, JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username","jdksa");
        jsonObject.put("pass","12334");
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonObject.toString());
        //构建request
        Request request = new Request.Builder().url("http://localhost:8081/alipaytest/test2").post(requestBody).build();
        //构建response
       Response response = client.newCall(request).execute();
        if (response.isSuccessful()){
            System.out.println(response.body().string());//非toString
        }else {
            throw new IOException("Unexpected code " + response);
        }
    }

    /**
     * 同步基于http的文件上传
     * 通过addFormDatapart可以添加多个上传的文件
     */
    @Test
    public void uploadMultiFiel() throws IOException {
        OkHttpClient client = new OkHttpClient();
        File file = new File("C:\\Users\\bbdw\\Desktop\\新建文件夹\\u=3300305952,1328708913&fm=26&gp=0.jpg");
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", "pic.png", fileBody)
                .build();
        Request request = new Request.Builder().url("http://localhost:8081/alipaytest/test3").post(requestBody).build();
        Response response = client.newCall(request).execute();
    }
    
    @Test
    public void test1() throws ParseException {
        String s="2019-07-11 11:54:2300";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
        Date parse1 = simpleDateFormat.parse(s);
        String s2 = simpleDateFormat.format(parse1);
        System.out.println(s2);
    }
}
