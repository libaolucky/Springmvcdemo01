package com.xiexin.controller;

import com.xiexin.bean.AdminInfo;
import com.xiexin.bean.Dog;
import com.xiexin.bean.Lover;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
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
    
    //ajax 接收 数组/集合
    @RequestMapping("/ajax03")
     @ResponseBody
    public Map ajax03(@RequestParam("ids[]") List<Integer> ids){ //前端 ids[]  后台是 ids
                        //当前后端的参数 不一样的时候，那么久需要使用注解调整
        for (Integer id : ids) {
            System.out.println("id = " + id);
        }
        Map codemap=new HashMap();
        codemap.put("code",0);
        codemap.put("msg","请求访问成功！");
        codemap.put("data",ids);
        return codemap;
    }

    // ajax 接收 JOSN对象
    @RequestMapping("/ajax04")
    @ResponseBody
    public Map ajax04(@RequestBody AdminInfo adminInfo){  //@RequestBody 注解指的是前端用的JSON请求
        System.out.println("adminInfo = " + adminInfo);
        Map codemap=new HashMap();
        codemap.put("code",0);
        codemap.put("msg","请求访问成功！");
        codemap.put("data",adminInfo);
        return codemap;

    }

    // ajax 05 接收前端传过来的多个对象
    @RequestMapping("/ajax05")
    @ResponseBody
    public Map ajax05(@ModelAttribute Lover lover, @ModelAttribute Dog dog){
        System.out.println("lover = " + lover);
        System.out.println("dog = " + dog);

        Map codemap=new HashMap();
        codemap.put("code",0);
        codemap.put("msg","请求访问成功！");
        codemap.put("data1",lover);
        codemap.put("data2",dog);
        return codemap;
    }

    //前端传过来多个 对象 需要根据 请求的前缀 进行绑定
    @InitBinder("lover")
    public void binding01(WebDataBinder webDataBinder){  //WebDataBinder 网络数据的绑定 也就是前端传过来的数据
        webDataBinder.setFieldDefaultPrefix("lover.");  //设置前缀  lover.name :
    }
    @InitBinder("dog")
    public void binding02(WebDataBinder webDataBinder){  //WebDataBinder 网络数据的绑定 也就是前端传过来的数据
        webDataBinder.setFieldDefaultPrefix("dog.");
    }

    // ajax06 json 收取多个对象
    @RequestMapping("/ajax06")
    @ResponseBody    //@GEtMapping 和 @RequestBody 不可以 同时使用，拿不到参数
    public Map ajax06(@RequestBody List<Lover> loverList){  //@RequestBody 他是从方法中拿取数据的，所以不能用GET请求
        for (Lover lover : loverList) {
            System.out.println("lover = " + lover);
        }
        Map codemap=new HashMap();
        codemap.put("code",0);
        codemap.put("msg","请求访问成功！");
        codemap.put("data",loverList);
        return codemap;
    }

    //ajax07 map
    @RequestMapping("/ajax07")
    @ResponseBody    //十分常用   servlet多表的 动态参数，就是用map   ajax07 能搞定一切！！
    public Map ajax07(@RequestBody Map map){
        System.out.println("map的adminName =" + map.get("adminName"));
        Map codemap=new HashMap();
        codemap.put("code",0);
        codemap.put("msg","请求访问成功！");
        codemap.put("data",map);
        return codemap;
    }

    @RequestMapping("/ajax08")
    @ResponseBody
    public Map ajax08(Lover lover,@RequestParam(value = "limit",required = false,defaultValue="5") Integer pageSize,Integer page){
        System.out.println("lover = " + lover);
        System.out.println("pageSize = " + pageSize);
        System.out.println("page = " + page);
        return null;
    }

   // 以上是 前后端 分离  最新 项目用到的知识点，那么也有 传统项目， 后台跳到另一个界面



    // 第一种的： springmvc 的传值方式，  原始方式：request + session + request的转发
    // 传统的mvc的方法（不返回json数据，不使用 @ResponseBody），他要跳转jsp的， 跳转jsp方式1的： 返回值是String
    // 页面传值： 即： 四大作用域  request session  applation, page
    @RequestMapping("/yuansheng")  //什么是页面传值  登录页（admin,123455）----->yuansheng
    //public String yuansheng(AdminInfo adminInfo, HttpSession session){
    public String yuansheng(HttpSession session, HttpServletRequest request){
        System.out.println("原生方式 页面传值");
        //System.out.println("adminInfo = " + adminInfo);
        //登录如果验证成功， 那就 需要把登录信息 放入到session 域当中
        //session.setAttribute("adminInfo",adminInfo);

       String adminName=request.getParameter("adminName");
       String adminPwd=request.getParameter("adminPwd");
       request.setAttribute("adminName",adminName);
       request.setAttribute("adminPwd",adminPwd);
       //servlet 转发
       //request.getRequestDispatcher("home.jsp").forward(request,response);
       // return "home"; //这个和 PageController 里的 查找jsp是没有联系的
       // return "forward:/WEB-INF/pages/home.jsp";
        //return "forward:/pages/home"; //springmvc 的转发
        // 重定向  servlet.sendDirect("/www.baidu.com")  重定向是携带不了数据
       //return "redirect:https://www.baidu.com";  //带/  和 不带 / 的区别
        return "redirect:/https://www.baidu.com"; //带/ 是直接在根目录下的  http://localhost:8080/https://www.baidu.com
    }


    //第二种 传值方式  modelAndView
    @RequestMapping("/modelAndView")
    public ModelAndView modelAndView(AdminInfo adminInfo){
       //  ModelAndView  模型和视图 通俗： 就是数据和显示  modelAndView 可以代替转发功能
        ModelAndView mv=new ModelAndView();
        mv.addObject("adminName",adminInfo.getAdminName());
        mv.addObject("adminPwd",adminInfo.getAdminPwd());
        System.out.println("以上是model 的绑定，即数据的绑定 ");
        mv.setViewName("home");
        return mv;
    }

    // 第三种： springmvc 的传值方式， model 代码少
    @RequestMapping("/model")
    public String model(AdminInfo adminInfo, Model model){
        model.addAttribute("adminName",adminInfo.getAdminName());
        model.addAttribute("adminPwd",adminInfo.getAdminPwd());
        return "home";
    }
    // 第四种  springmvc 的传值方式！！ modelAndMap
    @RequestMapping("/modelMap")
    public String modelMap(AdminInfo adminInfo, ModelMap modelMap){
        modelMap.put("adminName",adminInfo.getAdminName());
        modelMap.put("adminPwd",adminInfo.getAdminPwd());
        return "home";
    }

    //第五种 Springmvc 传值的方式  Map方式 灵活
    @RequestMapping("/map")
    public String map(AdminInfo adminInfo,Map<String, Object> map){
        map.put("adminName",adminInfo.getAdminName());
        map.put("adminPwd",adminInfo.getAdminPwd());
        return "home";
    }

}
