package com.bbdw.sharesession.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: bbdw
 * @Date: 2019/6/3 9:27
 * @Version 1.0
 */
@RestController
@RequestMapping("test")
public class QuickTest {

    @GetMapping("/test01")
    public Map<String,Object> test01(HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        request.getSession().setAttribute("requestUrl",request.getRequestURL());
        map.put("requestUrl",request.getRequestURL());
        return map;
    }
    @GetMapping("/getsessions")
    public Object sessions(HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        HttpSession session = request.getSession();
        session.setAttribute(session.getId(),"hello woshi user!");
        map.put("sessionId",request.getSession().getId());
        map.put("message",request.getServerPort());
        map.put("cookies",request.getCookies());
        return map;
    }
}
