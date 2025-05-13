package com.chenjiajin.controller;

import com.chenjiajin.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AopController {

    @Autowired
    private TestService service;

    @RequestMapping("/aop")
    public String aop(String test) {
        System.out.println("这是执行方法");
        return "success";
    }


    @RequestMapping("/aop2")
    public String aop2() {
        // http://localhost/aop2
        System.out.println("这是执行方法2");
        return "success2";
    }


}