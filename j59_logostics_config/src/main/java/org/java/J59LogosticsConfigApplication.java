package org.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class J59LogosticsConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(J59LogosticsConfigApplication.class, args);
    }

}
