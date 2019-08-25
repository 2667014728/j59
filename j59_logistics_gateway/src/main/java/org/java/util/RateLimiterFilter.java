package org.java.util;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

/**
 * @Author: 马果
 * @Date: 2019/7/10 09:44
 * @Description:
 * 限流（网络削峰）
 */
@Component
public class RateLimiterFilter extends ZuulFilter {

    //指定，每1秒钟，向令牌桶中，存放1000个令牌
    private final static RateLimiter rate_Limiter = RateLimiter.create(1000);

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER-2;
    }

    @Override
    public boolean shouldFilter() {
      /*  RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest req = context.getRequest();
        String uri = req.getRequestURI();
        if(uri.startsWith("/gateway/order")||uri.startsWith("/gateway/goods")){
            return true;
        }*/

        return false;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext context = RequestContext.getCurrentContext();

        //判断，当前是请求是否拥有令牌

        if(!rate_Limiter.tryAcquire()){ ////如果当前请求拥有令牌，返回true,没有令牌，返回false
            //当前请求，没有令牌
            context.setSendZuulResponse(false);//网关不再分发请求
            context.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());//标识当前网络请求过多
        }

        return null;
    }
}
