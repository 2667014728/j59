package org.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


@SpringBootApplication
public class J59LogisticsShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(J59LogisticsShiroApplication.class, args);
    }

}
