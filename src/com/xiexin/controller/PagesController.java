package com.xiexin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
* 该控制层 是为了  查找 jsp或者  带参数 访问 jsp 或者跳转的
*
* */
@Controller
@RequestMapping("/pages")
public class PagesController {
    @RequestMapping("/reg")  //layui的注册
    public String reg(){
        System.out.println("请求注册进入hello。。。了");
        return "reg";  //故意写的 强调  必须和XXX rello
    }

    @RequestMapping("/regForm")   //传统的注册
    public String regForm(){
        return "regForm";
    }

    @RequestMapping("/login")  //layui的登录
    public String login(){
        return "login";
    }

    @RequestMapping("/loginForm")  //传统的登录
    public String loginForm(){
        return "loginForm";
    }

    @RequestMapping("/ajaxCommit")   // ajax收参数的
    public String ajaxCommit(){
        return "ajaxCommit";
    }

}
