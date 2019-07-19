package com.lai.springclouddemo.util;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: bbdw
 * @Date: 2019/7/12 11:40
 * @Version 1.0
 */
public class RequestToJsonobjectUtil {

    public static JSONObject readjson(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        Map pmap = request.getParameterMap();
        //通过循环遍历的方式获得key和value并set到jsonobject中
        Iterator it = pmap.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next().toString();
            String[] values = (String[])pmap.get(key);
            jsonObject.put(key, values[0]);
        }
        return jsonObject;
    }
}
