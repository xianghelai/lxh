package com.lai.springclouddemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.lai.springclouddemo.pojo.AAA;
import com.lai.springclouddemo.util.RequestToJsonobjectUtil;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @Author: bbdw
 * @Date: 2019/7/4 10:25
 * @Version 1.0
 */
@Controller
public class demo1Controller {
    @GetMapping("/testpayform")
    public String testPayForm(){

        return "demo2";
    }
    @GetMapping("/testpayform2")
    public String testPayForm2(){

        return "demo1";
    }
    @GetMapping("/paysuccess")
    public String paysuccess(){

        return "paysuccess";
    }
    @GetMapping("test")
    @ResponseBody
    public String testget(HttpServletRequest request){
        JSONObject readjson = RequestToJsonobjectUtil.readjson(request);
        System.out.println(readjson);
        return "我是get请求";
    }
    @PostMapping("test")
    @ResponseBody
    public String testPost(HttpServletRequest request){
        JSONObject readjson = RequestToJsonobjectUtil.readjson(request);
        System.out.println(readjson);
        return "我是post请求";
    }
    @PostMapping("upload")
    @ResponseBody
    public String testPostUpload(HttpServletRequest request){

        boolean b = ServletFileUpload.isMultipartContent(request);
        System.out.println("是否为文件："+b);
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file1 = multipartRequest.getFile("file1");
        MultipartFile file2 = multipartRequest.getFile("file2");
        return "我是文件上传";
    }

    @PostMapping("test2")
    @ResponseBody
    public String testPost2(@RequestBody AAA request){
        //JSONObject readjson = RequestToJsonobjectUtil.readjson(request);
        System.out.println(request.getPass()+":"+request.getUsername());
        return "{a:我是post请求}";
    }

    @PostMapping("test3")
    @ResponseBody
    public String testPost3(HttpServletRequest request) throws IOException {
        boolean b = ServletFileUpload.isMultipartContent(request);
        System.out.println("是否为文件："+b);
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        MultipartFile image = fileMap.get("image");
        byte[] bytes = image.getBytes();
        InputStream inputStream = image.getInputStream();
        FileOutputStream fs = new FileOutputStream("C:\\Users\\bbdw\\Desktop\\新建文件夹\\download.png");

        byte[] by = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(by)) > 0)
                fs.write(by, 0, len);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "{a:我是文件上传}";
    }
}
