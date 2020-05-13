package com.city.android;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.city.base","com.city.android"})
@SpringBootApplication
public class OnlineEducationAndroidApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineEducationAndroidApplication.class, args);
    }

}
