package com.neon.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages="com.neon")
@EnableJpaRepositories("com.neon.repo")
@EntityScan("com.neon.model")
public class NeonApplication {
    public static void main(String[] args) {
        SpringApplication.run(NeonApplication.class, args);
    }
}
