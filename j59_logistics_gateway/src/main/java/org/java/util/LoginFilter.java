package org.java.util;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.catalina.filters.ExpiresFilter;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

/**
 * @Author: 马果
 * @Date: 2019/7/10 08:48
 * @Description:
 */
@Component
public class LoginFilter extends ZuulFilter {

    /**
     * 标识 过滤器的类型
     * @return
     */
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /**
     * 指定过滤器的执行顺序
     * 值越小，越优先
     * @return
     */
    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER-1;
    }

    /**
     * 该方法，用于判断，是否进入过滤器的主体
     * @return
     */
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;
    @Override
    public boolean shouldFilter() {

        //获得请求上下文
        RequestContext requestContext = RequestContext.getCurrentContext();
        //获得请求httpServletRequest
        HttpServletRequest request = requestContext.getRequest();
        //获得请求的路径
        String uri = request.getRequestURI(); //    /gateway/order/find/1/1
        System.out.println(uri);
        if(uri.startsWith("/gateway")){
            if (uri.contains("/js")||uri.contains("/css")||uri.contains("/img")){
                return false;
            }
            System.out.println("000000000000000000000000000000000000000000000000000000000");
            return true;//返回true,表示，要执行过滤器的主体,系统会自动进入run方法

        }
        System.out.println("--------------------------------------------------");
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        //在此处，判断，用户是否登录

        //如果用户登录了，可以把用户信息，存放在token中，此时，如果当前地址需要用户登录才允许访问，我们可以从request中，获取，判断，当前用户是否拥token,
        //如果用户没有登录,request中，将取不到令牌

        //获得请求上下文
        RequestContext requestContext = RequestContext.getCurrentContext();

        //获得请求httpServletRequest
        HttpServletRequest request = requestContext.getRequest();

        HttpServletResponse response = requestContext.getResponse();
        HttpSession ses=request.getSession();
        String url = request.getRequestURL().toString();
        //先从请求头中获得toke
        Map<String,String> user = (Map<String, String>) ses.getAttribute("usera");

        if(user==null) {//如果令牌为空，再用request.getParameter来获取


            Cookie[] cookie = request.getCookies();//获取的是请求里的所有cookie组成的数组
            Cookie cookie1=null;
            if (cookie!=null){
                for(int i=0;i<cookie.length;i++){
                    if("username".equals(cookie[i].getName())){
                        System.out.println("找到cookie1了");
                        cookie1=cookie[i];
                        break;
                    }
                }
            }

            String username=null;
            if (cookie1!=null){
                 username=cookie1.getValue();
            }
            System.out.println(username);
            //设置redisTemplate序列化方式
            RedisSerializer redisSerializer = new StringRedisSerializer();
            redisTemplate.setKeySerializer(redisSerializer);
            System.out.println("读取缓存中用户信息"+username);
            Map<String,String> user1 =null;
            if (username!=null){
                user1=(Map<String, String>) redisTemplate.opsForHash().get(username,"dl" );
            }
            ses.setAttribute("usera",user1);
            if (user1==null){

                if (url.contains("/login")||url.contains("/register")){

                    return null;
                }else {
               /* //过滤器不再向执行调用服务
                requestContext.setSendZuulResponse(false);//不再调用对应服务
                //抛出一个错误信息
                requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value()); //如果没有登录，会看见一个401的错误代码*/
                    try {
                        response.sendRedirect("http://localhost:9000/gateway/shiro/login");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }else {

             /*   requestContext.setSendZuulResponse(true);
                requestContext.setResponseStatusCode(200);*/

            }


        }

        return null;
    }
}
