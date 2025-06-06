package com.themoment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.themoment")
public class BackendEumApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendEumApplication.class, args);
    }

}
