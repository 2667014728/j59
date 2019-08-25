package org.java.web;

import org.java.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class UserController {


     @Autowired
    private InfoService infoService;
     @Autowired
     private HttpServletRequest request;
     @Autowired
     private HttpServletResponse response;



    @RequestMapping("login")
    public String login(HttpServletRequest request) throws Exception {

        System.out.println("22222222222");
        //如果登录失败，request中，会存放一个参数: shiroLoginFailure,如果用户还没有登录，该值为null
        String err=null;
        err = (String) request.getAttribute("shiroLoginFailure");
        if (err == null) {
            System.out.println("@@@@@@@@@@@@@@@@没有登录");
            HttpSession ses=request.getSession();

            if ( ses.getAttribute("usera")!=null){
                return "leftmenu";

            }
        } else {
            //UnknownAccountException//用户名不存在
            // IncorrectCredentialsException//密码不匹配
//            System.out.println("@@@@@@@@@@@@@@@@登录失败进入");
//            System.out.println("错误原因:"+err);
            if(err.equals("org.apache.shiro.authc.UnknownAccountException")){
                throw new Exception("用户名不存在");
            }
            if(err.equals("org.apache.shiro.authc.IncorrectCredentialsException")){
                throw new Exception("密码错误");
            }


        }
        return "/register";
    }

    @GetMapping("{target}")
    public String forward(@PathVariable("target") String target){
        return "/"+target;
    }





}
