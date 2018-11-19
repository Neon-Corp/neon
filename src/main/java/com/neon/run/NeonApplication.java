package com.neon.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages="com.neon")
@EntityScan("com.neon.model")
public class NeonApplication {
    public static void main(String[] args) {
        SpringApplication.run(NeonApplication.class, args);
    }
}
