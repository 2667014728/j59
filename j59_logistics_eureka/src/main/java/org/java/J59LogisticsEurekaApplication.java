package org.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class J59LogisticsEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(J59LogisticsEurekaApplication.class, args);
    }

}
