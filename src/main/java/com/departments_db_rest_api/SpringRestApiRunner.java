package com.departments_db_rest_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.departments_db_rest_api")
public class SpringRestApiRunner {

    public static void main(String... args) {
        SpringApplication.run(SpringRestApiRunner.class);

    }

}
