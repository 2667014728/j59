package org.java.ex;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 马果
 * @Date: 2019/6/27 14:55
 * @Description:
 * 全局异常处理器，处理所有的异常
 */
@ControllerAdvice//用异常，将会通知该异常类进行处理
public class GlobalDefaultExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public String handleException(HttpServletRequest request,Exception ex){
        //获得异常的原因
        String msg = ex.getMessage();
        request.setAttribute("msg",msg );
        return "/err";
    }
}
