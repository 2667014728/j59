package org.java.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * @Author: 马果
 * @Date: 2019/6/27 14:11
 * @Description:
 */
@Controller
public class FirstController {
    @GetMapping("/first")
    public String first(HttpSession session){
        System.out.println("-------------------------------------------");
        //获得认证的主体
        Subject subject = SecurityUtils.getSubject();
        //从主体获得凭证
        String name = (String) subject.getPrincipal();

        session.setAttribute("name",name );

        return "/main";
    }
}
