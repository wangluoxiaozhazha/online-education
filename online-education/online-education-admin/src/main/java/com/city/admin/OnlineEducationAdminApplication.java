package com.city.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.city.base","com.city.admin"})
@SpringBootApplication
public class OnlineEducationAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineEducationAdminApplication.class, args);
    }

}
