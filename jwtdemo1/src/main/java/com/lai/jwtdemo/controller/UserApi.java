package com.lai.jwtdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.lai.jwtdemo.annotation.PassToken;
import com.lai.jwtdemo.annotation.UserLoginToken;
import com.lai.jwtdemo.pojo.User;
import com.lai.jwtdemo.service.UserService;
import com.lai.jwtdemo.utils.Jwtutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: bbdw
 * @Date: 2019/7/17 11:25
 * @Version 1.0
 */
@RestController
@RequestMapping("api")
public class UserApi {
    @Autowired
    private UserService userService;
    @Autowired
    private Jwtutil jwtutil;

    @PostMapping("/login")
    public Object login(@RequestBody User user){
        JSONObject jsonpObject = new JSONObject();
        User userfromDB = userService.findByUsername(user);
        if(userfromDB == null){
            jsonpObject.put("message","登陆失败，用户不存在");
            return jsonpObject;
        }else{
            if(!userfromDB.getPassword().equals(user.getPassword())){
                jsonpObject.put("message","登陆失败，密码错误");
                return jsonpObject;
            }else {
                String token = jwtutil.getToken(userfromDB);
                jsonpObject.put("token",token);
                jsonpObject.put("user",userfromDB);
                return jsonpObject;
            }
        }
    }
    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }

    //@UserLoginToken
    @PassToken
    @GetMapping("/getMessage2")
    public String getMessage2(){
        return "你已通过验证2";
    }
}
