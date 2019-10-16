package com.lai.shirotags.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author bbdw
 * @date 2019/10/10 20:44
 */
@Controller
public class formController {
    @GetMapping("/toform")
    public String toform(){
        return "formValidate";
    }
}
