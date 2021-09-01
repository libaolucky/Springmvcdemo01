package com.xiexin.controller;

import com.xiexin.bean.AdminInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/admin")
public class AdminController {
 // 注册成功后，如果是单体项目 -- 就要跳转到登录页面， 这个跳转是后台转发，重定向，总之
    //后台负责 跳转， 携带数据的跳转页面的
    // 如果是 新型的项目， 及前后端分离的， 那么 后台 只负责 返回给前端json数据
    // 跳转 是 前端来处理的，前端根据 后台返回的code代码，进行跳转
    //如果前端负责跳转，他会起一个好听的名子，叫 路由
    // 项目上的分离： 前端一个项目，后台一个项目 2 个项目 他们的认证是
    //数据上的分离：还是一个项目，只不过 前后端用json来交互数据token/jwt +redis
    // 很少在用 jstl/el 表达式 来写项目。 他们的认证是 session

    // layui 在ssm 框架中的使用，其实是 数据上的分离，也可以项目上的分离
    // 那么 他就是json交互的，那么 后台只需要 给他 返回json数据就可以了

    // 以前 在servlet中， resp.getWriter().print(new JSONObject.toString(map)) 输出json
    //现在 用 mvc框架 高级了
//    adminName: 是哪里看   以前收参数：req.getParamter("adminName")
//    adminPwd: dfdfdf
//    adminPwdR: dfdgg

    // 第一种收参方式： 数据类型接收参数！！
    @RequestMapping("/reg")  //layui版的
    @ResponseBody //这个注解  就是返回给前端的json
    public Map reg(String adminName, String adminPwd, String adminPwdR, String adminTime) {
        System.out.println("adminPwdR = " + adminPwdR);
        System.out.println("adminTime = " + adminTime);

        Map codemap = new HashMap();
        if (!adminPwd.equals(adminPwdR)) {
            codemap.put("code", "4401");
            codemap.put("msg", "你输入的密码和重复密码不一致，注册不成功");
            return codemap;
        }
        if(adminName.length()<=0){
            codemap.put("code", "4202");
            codemap.put("msg", "adminName表单需要写全乎");
            return codemap;
        }
        if( adminPwd.length()<=0){
            codemap.put("code", "4202");
            codemap.put("msg", "admidPwd需要写全乎");
            return codemap;
        }

        codemap.put("code", 0);
        codemap.put("msg", "注册成功！");
        return codemap;
    }

    //传统版本的  不返回json 跳转使用转发 或者 重定向
    @RequestMapping("/regFrom")
    public String regFrom(String adminName,String adminPwd){
        System.out.println("adminName = " + adminName);
        System.out.println("adminPwd = " + adminPwd);
        //注册成功跳转到登录页
        return "loginForm";

    }

    //第二种收参数的方式，用的叫做 实体类 收参数
    @RequestMapping("/regByBean")
    @ResponseBody
    public Map regByBean(AdminInfo adminInfo){
        System.out.println("adminInfo = " + adminInfo);
        Map codemap=new HashMap();
        codemap.put("code",0);
        codemap.put("msg","注册成功！");
        return codemap;


    }
}
