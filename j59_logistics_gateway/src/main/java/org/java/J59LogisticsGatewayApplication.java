package org.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCaching
@EnableZuulProxy
@SpringBootApplication
public class J59LogisticsGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(J59LogisticsGatewayApplication.class, args);
    }

}
